package com.kino.service;

import com.kino.entity.Reservierung;
import com.kino.entity.ReservierungSitz;
import com.kino.entity.Sitz;
import com.kino.entity.Sitzreihe;
import com.kino.entity.Sitzkategorie;
import com.kino.entity.Sitzstatus;
import com.kino.entity.Vorstellung;
import com.kino.entity.Saal;
import com.kino.repository.ReservierungRepository;
import com.kino.repository.SitzRepository;
import com.kino.repository.VorstellungRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservierungServiceTest {

    @Mock
    private ReservierungRepository reservierungRepository;
    @Mock
    private VorstellungRepository vorstellungRepository;
    @Mock
    private SitzRepository sitzRepository;
    @InjectMocks
    private ReservierungService reservierungService;

    private Vorstellung vorstellung;
    private Saal saal;
    private Sitzreihe sitzreihe;
    private Sitz seat1, seat2, seat3;
    private final String kundenEmail = "test@example.com";

    @BeforeEach
    void setUp() {
        vorstellung = new Vorstellung();
        vorstellung.setId(1L);
        vorstellung.setFilmTitel("TestFilm");
        vorstellung.setStartzeit(LocalTime.of(23, 59));
        vorstellung.setDauerMinuten(120);
        saal = new Saal();
        saal.setIstFreigegeben(true);
        sitzreihe = new Sitzreihe();
        seat1 = new Sitz();
        seat1.setStatus(Sitzstatus.FREI);
        seat1.setKategorie(Sitzkategorie.PARKETT);
        seat2 = new Sitz();
        seat2.setStatus(Sitzstatus.FREI);
        seat2.setKategorie(Sitzkategorie.PARKETT);
        seat3 = new Sitz();
        seat3.setStatus(Sitzstatus.FREI);
        seat3.setKategorie(Sitzkategorie.LOGE);
        sitzreihe.setSitze(Arrays.asList(seat1, seat2, seat3));
        saal.setSitzreihen(Collections.singletonList(sitzreihe));
        vorstellung.setSaal(saal);
    }

    @Test
    void reservierungAnlegenNormalCase() {
        int anzahl = 2;
        String datum = LocalDate.now().toString();
        when(vorstellungRepository.findById(1L)).thenReturn(Optional.of(vorstellung));
        when(reservierungRepository.save(any(Reservierung.class))).thenAnswer(invocation -> {
            Reservierung r = invocation.getArgument(0);
            r.setId(100L);
            return r;
        });
        Reservierung result = reservierungService.reservierungAnlegen(1L, "PARKETT", anzahl, kundenEmail, datum, "RESERVIERT");
        assertNotNull(result);
        assertEquals("RESERVIERT", result.getStatus());
        assertEquals(kundenEmail, result.getKundenEmail());
        assertEquals(2, result.getReservierungSitze().size());
        assertEquals(Sitzstatus.RESERVIERT, seat1.getStatus());
        assertEquals(Sitzstatus.RESERVIERT, seat2.getStatus());
        assertEquals(Sitzstatus.FREI, seat3.getStatus());
    }

    @Test
    void reservierungAnlegenNegativeAnzahl() {
        String datum = LocalDate.now().toString();
        Exception ex = assertThrows(RuntimeException.class, () ->
                reservierungService.reservierungAnlegen(1L, "PARKETT", -1, kundenEmail, datum, "RESERVIERT")
        );
        assertNotNull(ex.getMessage());
    }

    @Test
    void reservierungAnlegenNullVorstellung() {
        when(vorstellungRepository.findById(anyLong())).thenReturn(Optional.empty());
        String datum = LocalDate.now().toString();
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                reservierungService.reservierungAnlegen(999L, "PARKETT", 2, kundenEmail, datum, "RESERVIERT")
        );
        assertEquals("Vorstellung mit ID 999 not found!", ex.getMessage().replace("nicht gefunden!", "not found!"));
    }
}

