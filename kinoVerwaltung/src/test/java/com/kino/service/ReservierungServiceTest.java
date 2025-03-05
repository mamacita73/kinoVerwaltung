package com.kino.service;

import com.kino.entity.*;
import com.kino.repository.ReservierungRepository;
import com.kino.repository.SitzRepository;
import com.kino.repository.VorstellungRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservierungServiceTest {

    @Mock
    private ReservierungRepository reservierungRepository;

    @Mock
    private VorstellungRepository vorstellungRepository;

    @Mock
    private SitzRepository sitzRepository;

    @InjectMocks
    private ReservierungService service;

    private Vorstellung vorstellung;

    @BeforeEach
    void setUpTestData() {
        // Beispiel-Vorstellung anlegen
        vorstellung = new Vorstellung();
        vorstellung.setId(1L);

        // Beispiel-Saal mit Sitzreihen und freien Sitzen
        Saal saal = new Saal();
        saal.setIstFreigegeben(true);

        Sitzreihe sr = new Sitzreihe();
        List<Sitz> sitze = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Sitz sitz = new Sitz();
            sitz.setKategorie(Sitzkategorie.PARKETT);
            sitz.setStatus(Sitzstatus.FREI);
            sitze.add(sitz);
        }
        sr.setSitze(sitze);
        saal.setSitzreihen(List.of(sr));

        vorstellung.setSaal(saal);
    }

    // --- Tests für reservierungAnlegen() ---

//    @Test
//    void reservierungAnlegenNormalFall() {
//        // Mock: VorstellungRepository soll bei findById(1L) unsere Test-Vorstellung zurückliefern
//        when(vorstellungRepository.findById(1L)).thenReturn(Optional.of(vorstellung));
//
//        // Mock: ReservierungRepository.save() gibt dieselbe Reservierung zurück,
//        // aber wir simulieren, dass in der DB eine ID generiert wird (z. B. 10L).
//        when(reservierungRepository.save(any(Reservierung.class))).thenAnswer(invocation -> {
//            Reservierung r = invocation.getArgument(0);
//            r.setId(10L);
//            return r;
//        });
//
//        // Aufruf der Methode
//        Reservierung result = service.reservierungAnlegen(
//                1L,                 // vorstellungId
//                "PARKETT",          // Kategorie
//                3,                  // Anzahl Sitze
//                "kunde@example.com",
//                "2025-03-03",
//                "OFFEN"
//        );
//
//        // Prüfe, ob das Ergebnis korrekt ist
//        assertNotNull(result);
//        assertEquals(10L, result.getId());
//        assertEquals("kunde@example.com", result.getKundenEmail());
//        assertEquals("2025-03-03", result.getDatum());
//        assertEquals("OFFEN", result.getStatus());
//        assertNotNull(result.getReservierungsnummer());
//
//        // Die gewünschten 3 Sitze sollten reserviert sein
//        assertEquals(3, result.getReservierungSitze().size());
//        for (ReservierungSitz rs : result.getReservierungSitze()) {
//            assertEquals(Sitzstatus.RESERVIERT, rs.getSitz().getStatus());
//        }
//    }

    @Test
    void reservierungAnlegenVorstellungNotFound() {
        // Mock: VorstellungRepository.findById(...) liefert leeres Optional -> Vorstellung nicht vorhanden
        when(vorstellungRepository.findById(999L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.reservierungAnlegen(
                    999L,
                    "PARKETT",
                    2,
                    "kunde@example.com",
                    "2025-03-03",
                    "OFFEN"
            );
        });
        assertEquals("Vorstellung mit ID 999 nicht gefunden!", exception.getMessage());
    }

//    @Test
//    void reservierungAnlegenNotEnoughFreeSeats() {
//        // Mock: Vorstellung hat nur 2 freie Sitze, wir wollen 3
//        when(vorstellungRepository.findById(1L)).thenReturn(Optional.of(vorstellung));
//
//        // Nur 2 Sitze auf FREI setzen (restliche 3 Sitze auf RESERVIERT)
//        List<Sitz> sitze = vorstellung.getSaal().getSitzreihen().get(0).getSitze();
//        sitze.get(2).setStatus(Sitzstatus.RESERVIERT);
//        sitze.get(3).setStatus(Sitzstatus.RESERVIERT);
//        sitze.get(4).setStatus(Sitzstatus.RESERVIERT);
//
//        assertThrows(IllegalStateException.class, () -> {
//            service.reservierungAnlegen(
//                    1L,
//                    "PARKETT",
//                    3,
//                    "kunde@example.com",
//                    "2025-03-03",
//                    "OFFEN"
//            );
//        });
//    }

    // --- Tests für stornieren() ---

    @Test
    void stornierenNormalFall() {
        // Reservierung anlegen
        Reservierung reservierung = new Reservierung();
        reservierung.setId(50L);
        reservierung.setStatus("OFFEN");

        // Zwei reservierte Sitze
        Sitz sitz1 = new Sitz();
        sitz1.setStatus(Sitzstatus.RESERVIERT);

        Sitz sitz2 = new Sitz();
        sitz2.setStatus(Sitzstatus.RESERVIERT);

        ReservierungSitz rs1 = new ReservierungSitz();
        rs1.setReservierung(reservierung);
        rs1.setSitz(sitz1);

        ReservierungSitz rs2 = new ReservierungSitz();
        rs2.setReservierung(reservierung);
        rs2.setSitz(sitz2);

        reservierung.setReservierungSitze(List.of(rs1, rs2));

        // Mock: findById(50L) gibt unsere Reservierung zurück
        when(reservierungRepository.findById(50L)).thenReturn(Optional.of(reservierung));

        // Mock: save() gibt dieselbe Reservierung zurück
        when(reservierungRepository.save(any(Reservierung.class))).thenAnswer(invocation -> invocation.getArgument(0));

        String result = service.stornieren(50L);
        assertEquals("Reservierung 50 storniert.", result);
        assertEquals("STORNIERT", reservierung.getStatus());

        // Sitze sollten nun FREI sein
        for (ReservierungSitz rs : reservierung.getReservierungSitze()) {
            assertEquals(Sitzstatus.FREI, rs.getSitz().getStatus());
        }
    }

    @Test
    void stornierenReservierungNotFound() {
        // Mock: findById(...) liefert leer -> nicht gefunden
        when(reservierungRepository.findById(999L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            service.stornieren(999L);
        });
        assertEquals("Reservierung nicht gefunden!", exception.getMessage());
    }

    // --- Tests für getReservierungenByEmail() ---

    @Test
    void getReservierungenByEmail() {
        // Lege zwei Reservierungen mit derselben Kunden-Email an
        Reservierung r1 = new Reservierung();
        r1.setId(101L);
        r1.setKundenEmail("kunde@example.com");

        Reservierung r2 = new Reservierung();
        r2.setId(102L);
        r2.setKundenEmail("kunde@example.com");

        // Eine Reservierung mit anderer Email
        Reservierung r3 = new Reservierung();
        r3.setId(103L);
        r3.setKundenEmail("andere@example.com");

        // Mock: findByKundenEmail("kunde@example.com") gibt r1 und r2 zurück
        when(reservierungRepository.findByKundenEmail("kunde@example.com"))
                .thenReturn(List.of(r1, r2));

        List<Reservierung> result = service.getReservierungenByEmail("kunde@example.com");
        assertEquals(2, result.size());
    }
}
