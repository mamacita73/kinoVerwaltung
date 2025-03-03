package com.kino.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kino.entity.*;
import com.kino.repository.BenutzerRepository;
import com.kino.repository.BuchungRepository;
import com.kino.repository.ReservierungRepository;
import com.kino.repository.VorstellungRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuchungServiceTest {

    @Mock
    private BuchungRepository buchungRepository;
    @Mock
    private ReservierungRepository reservierungRepository;
    @Mock
    private VorstellungRepository vorstellungRepository;
    @Mock
    private RabbitTemplate rabbitTemplate;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private BenutzerRepository benutzerRepository;

    @InjectMocks
    private BuchungService buchungService;

    private Vorstellung vorstellung;
    private Saal saal;
    private Sitzreihe sitzreihe;
    private Sitz seat1, seat2, seat3;
    private Benutzer benutzer;

    @BeforeEach
    void setUp() {
        // Vorstellung mit Saal einrichten
        vorstellung = new Vorstellung();
        vorstellung.setId(10L);
        vorstellung.setFilmTitel("TestFilm");

        saal = new Saal();
        saal.setIstFreigegeben(true);
        sitzreihe = new Sitzreihe();

        // Erstelle 3 Sitze:
        // seat1 und seat2 sind FREI und haben Kategorie PARKETT (gewünscht)
        seat1 = new Sitz();
        seat1.setStatus(Sitzstatus.FREI);
        seat1.setKategorie(Sitzkategorie.PARKETT);

        seat2 = new Sitz();
        seat2.setStatus(Sitzstatus.FREI);
        seat2.setKategorie(Sitzkategorie.PARKETT);

        // seat3 hat eine andere Kategorie (LOGE) und wird nicht genutzt, wenn nach PARKETT gesucht wird
        seat3 = new Sitz();
        seat3.setStatus(Sitzstatus.FREI);
        seat3.setKategorie(Sitzkategorie.LOGE);

        sitzreihe.setSitze(Arrays.asList(seat1, seat2, seat3));
        saal.setSitzreihen(Collections.singletonList(sitzreihe));
        vorstellung.setSaal(saal);

        // Benutzer einrichten
        benutzer = new Benutzer();
        benutzer.setId(100L);
        benutzer.setEmail("test@example.com");
    }

    // ------------------------ buchungAnlegen ------------------------

    @Test
    void buchungAnlegenNormalFall() throws Exception {
        Long vorstellungId = 10L;
        String kategorie = "PARKETT";
        int anzahl = 2;
        String kundenEmail = "test@example.com";

        // Stubs für die benötigten Abhängigkeiten
        when(vorstellungRepository.findById(vorstellungId)).thenReturn(Optional.of(vorstellung));
        when(benutzerRepository.findByEmail(kundenEmail)).thenReturn(Optional.of(benutzer));
        // Simuliere, dass beim Speichern eine ID vergeben wird
        when(buchungRepository.save(any(Buchung.class))).thenAnswer(invocation -> {
            Buchung b = invocation.getArgument(0);
            b.setId(200L);
            return b;
        });
        // Simuliere die Umwandlung ins JSON für das Stats-Event
        when(objectMapper.writeValueAsString(any())).thenReturn("dummyJson");

        // Aufruf der Methode
        Buchung result = buchungService.buchungAnlegen(vorstellungId, kategorie, anzahl, kundenEmail);

        // Prüfungen:
        assertNotNull(result);
        assertEquals("GEBUCHT", result.getStatus());
        // Für PARKETT: Basispreis 7 + 1 = 8 pro Sitz, also 2 * 8 = 16 gesamt
        assertEquals(16, result.getSumme());
        assertEquals(200L, result.getId());
        assertEquals(vorstellung, result.getVorstellung());
        assertEquals(2, result.getBuchungSitze().size());

        // Die beiden für die Buchung genutzten Sitze (seat1 und seat2) müssen den Status GEBUCHT haben
        assertEquals(Sitzstatus.GEBUCHT, seat1.getStatus());
        assertEquals(Sitzstatus.GEBUCHT, seat2.getStatus());
        // seat3 bleibt FREI, da es nicht in die Auswahl ging
        assertEquals(Sitzstatus.FREI, seat3.getStatus());

        // Überprüfe, ob das Stats-Event gesendet wurde
        verify(rabbitTemplate, times(1)).convertAndSend("bookingStatsQueue", "dummyJson");
    }

    @Test
    void buchungAnlegenNichtGenugFreieSitze() {
        // Falls mehr Sitze angefordert werden als verfügbar
        Long vorstellungId = 10L;
        String kategorie = "PARKETT";
        int anzahl = 3; // Es gibt nur 2 Sitze in PARKETT
        String kundenEmail = "test@example.com";

        when(vorstellungRepository.findById(vorstellungId)).thenReturn(Optional.of(vorstellung));

        Exception ex = assertThrows(RuntimeException.class, () ->
                buchungService.buchungAnlegen(vorstellungId, kategorie, anzahl, kundenEmail)
        );
        assertEquals("Nicht genügend freie Plätze in PARKETT", ex.getMessage());
    }

    // ------------------------ reservierungZuBuchung ------------------------

    @Test
    void reservierungZuBuchungNormalFall() throws Exception {
        // Reservierung mit Status RESERVIERT anlegen, die zwei ReservierungSitze enthält
        Reservierung reservierung = new Reservierung();
        reservierung.setId(300L);
        reservierung.setStatus("RESERVIERT");
        reservierung.setKundenEmail("test@example.com");
        reservierung.setVorstellung(vorstellung);

        // Erstelle zwei reservierte Sitze
        Sitz resSeat1 = new Sitz();
        resSeat1.setStatus(Sitzstatus.RESERVIERT);
        resSeat1.setKategorie(Sitzkategorie.PARKETT);

        Sitz resSeat2 = new Sitz();
        resSeat2.setStatus(Sitzstatus.RESERVIERT);
        resSeat2.setKategorie(Sitzkategorie.PARKETT);

        ReservierungSitz rs1 = new ReservierungSitz();
        rs1.setSitz(resSeat1);
        ReservierungSitz rs2 = new ReservierungSitz();
        rs2.setSitz(resSeat2);

        reservierung.setReservierungSitze(Arrays.asList(rs1, rs2));

        when(reservierungRepository.findById(300L)).thenReturn(Optional.of(reservierung));
        when(buchungRepository.save(any(Buchung.class))).thenAnswer(invocation -> {
            Buchung b = invocation.getArgument(0);
            b.setId(400L);
            return b;
        });
        when(objectMapper.writeValueAsString(any())).thenReturn("dummyJson");

        Buchung result = buchungService.reservierungZuBuchung(300L);

        // Prüfungen:
        assertNotNull(result);
        assertEquals("GEBUCHT", result.getStatus());
        // Für PARKETT: Preis 8 pro Sitz, also 2 * 8 = 16
        assertEquals(16, result.getSumme());
        assertEquals(400L, result.getId());
        assertEquals("test@example.com", result.getKundenEmail());
        assertEquals(2, result.getBuchungSitze().size());
        // Die Referenz zur ursprünglichen Reservierung muss gesetzt sein
        assertEquals(reservierung, result.getReservierung());

        // Die Sitze müssen von RESERVIERT zu GEBUCHT geändert worden sein
        assertEquals(Sitzstatus.GEBUCHT, resSeat1.getStatus());
        assertEquals(Sitzstatus.GEBUCHT, resSeat2.getStatus());

        // Überprüfe, ob die Reservierung auf "ABGESCHLOSSEN" gesetzt wurde
        verify(reservierungRepository, times(1))
                .save(argThat(r -> "ABGESCHLOSSEN".equals(r.getStatus())));
        verify(rabbitTemplate, times(1)).convertAndSend("bookingStatsQueue", "dummyJson");
    }

    @Test
    void reservierungZuBuchungFalscherStatus() {
        // Reservierung mit einem falschen Status (nicht RESERVIERT)
        Reservierung reservierung = new Reservierung();
        reservierung.setId(301L);
        reservierung.setStatus("NICHT_RESERVIERT");

        when(reservierungRepository.findById(301L)).thenReturn(Optional.of(reservierung));

        Exception ex = assertThrows(RuntimeException.class, () ->
                buchungService.reservierungZuBuchung(301L)
        );
        assertEquals("Reservierung ist nicht im Status RESERVIERT!", ex.getMessage());
    }

    @Test
    void reservierungZuBuchungSitzNichtReserviert() {
        // Reservierung, bei der mindestens ein Sitz nicht mehr den Status RESERVIERT hat
        Reservierung reservierung = new Reservierung();
        reservierung.setId(302L);
        reservierung.setStatus("RESERVIERT");
        reservierung.setKundenEmail("test@example.com");
        reservierung.setVorstellung(vorstellung);

        Sitz resSeat1 = new Sitz();
        resSeat1.setStatus(Sitzstatus.RESERVIERT);
        resSeat1.setKategorie(Sitzkategorie.PARKETT);

        // Dieser Sitz ist FREI statt RESERVIERT
        Sitz resSeat2 = new Sitz();
        resSeat2.setStatus(Sitzstatus.FREI);
        resSeat2.setKategorie(Sitzkategorie.PARKETT);

        ReservierungSitz rs1 = new ReservierungSitz();
        rs1.setSitz(resSeat1);
        ReservierungSitz rs2 = new ReservierungSitz();
        rs2.setSitz(resSeat2);

        reservierung.setReservierungSitze(Arrays.asList(rs1, rs2));

        when(reservierungRepository.findById(302L)).thenReturn(Optional.of(reservierung));

        Exception ex = assertThrows(RuntimeException.class, () ->
                buchungService.reservierungZuBuchung(302L)
        );
        assertEquals("Mindestens ein reservierter Sitz ist nicht mehr RESERVIERT!", ex.getMessage());
    }
}
