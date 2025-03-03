package com.kino.service;

import com.kino.entity.Saal;
import com.kino.entity.Sitz;
import com.kino.entity.Sitzreihe;
import com.kino.repository.SaalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaalServiceTest {

    @Mock
    private SaalRepository saalRepository;

    @InjectMocks
    private SaalService saalService;

    private Saal saal;

    @BeforeEach
    void setUpTestData() {
        saal = new Saal();
        saal.setId(1L);
        saal.setName("Saal A");
        saal.setIstFreigegeben(true);
    }

    // --- Tests für getAllSäle() ---

    @Test
    void getAllSäle() {
        List<Saal> mockSaele = Arrays.asList(new Saal(), new Saal());
        when(saalRepository.findAll()).thenReturn(mockSaele);

        List<Saal> result = saalService.getAllSäle();
        assertEquals(2, result.size());
        verify(saalRepository, times(1)).findAll();
    }

    // --- Tests für getSaalById() ---

    @Test
    void getSaalByIdFound() {
        when(saalRepository.findById(1L)).thenReturn(Optional.of(saal));

        Optional<Saal> result = saalService.getSaalById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(saalRepository, times(1)).findById(1L);
    }

    @Test
    void getSaalByIdNotFound() {
        when(saalRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Saal> result = saalService.getSaalById(999L);
        assertFalse(result.isPresent());
        verify(saalRepository, times(1)).findById(999L);
    }

    // --- Tests für getSäleByKinoId() ---

    @Test
    void getSäleByKinoId() {
        Long kinoId = 10L;
        List<Saal> mockSaele = Arrays.asList(new Saal(), new Saal());
        when(saalRepository.findByKinoId(kinoId)).thenReturn(mockSaele);

        List<Saal> result = saalService.getSäleByKinoId(kinoId);
        assertEquals(2, result.size());
        verify(saalRepository, times(1)).findByKinoId(kinoId);
    }

    // --- Tests für saveSaal() ---

    @Test
    void saveSaal() {
        when(saalRepository.save(saal)).thenReturn(saal);

        Saal result = saalService.saveSaal(saal);
        assertNotNull(result);
        assertEquals("Saal A", result.getName());
        verify(saalRepository, times(1)).save(saal);
    }

    // --- Tests für deleteSaal() ---

    @Test
    void deleteSaal() {
        doNothing().when(saalRepository).deleteById(1L);
        saalService.deleteSaal(1L);
        verify(saalRepository, times(1)).deleteById(1L);
    }

    // --- Tests für getAllSaeleMitVorstellungen() ---

    @Test
    void getAllSaeleMitVorstellungen() {
        // Angenommen, diese Methode liefert uns 2 Säle zurück
        when(saalRepository.findAllSaeleMitVorstellungen())
                .thenReturn(Arrays.asList(new Saal(), new Saal()));

        List<Saal> result = saalService.getAllSaeleMitVorstellungen();
        assertEquals(2, result.size());
        verify(saalRepository, times(1)).findAllSaeleMitVorstellungen();
    }

    // --- Tests für anlegenSaal() ---

    @Test
    void anlegenSaalNormalFall() {
        // Beispiel: 1 Sitzreihe mit 2 Sitzen
        Sitzreihe sr = new Sitzreihe();
        sr.setId(100L);
        sr.setSitze(Arrays.asList(new Sitz(), new Sitz()));

        saal.setSitzreihen(Collections.singletonList(sr));

        // Mock: Speichern gibt dasselbe Objekt zurück,
        // wir simulieren, dass Saal in der DB eine ID 1L behält
        when(saalRepository.save(any(Saal.class))).thenAnswer(invocation -> {
            Saal s = invocation.getArgument(0);
            // Du kannst s.setId(...) setzen, falls der Saal noch keine ID hatte
            return s;
        });

        Saal result = saalService.anlegenSaal(saal);

        // Überprüfen, dass die Sitzreihe dem Saal zugeordnet wurde
        assertEquals(saal, sr.getSaal());
        // Die Anzahl der Sitze in der Reihe muss 2 sein
        assertEquals(2, sr.getAnzahlSitze());
        // Beide Sitze haben eine Referenz auf ihre Sitzreihe
        for (Sitz sitz : sr.getSitze()) {
            assertEquals(sr, sitz.getSitzreihe());
        }
        // Das gespeicherte Objekt ist nicht null
        assertNotNull(result);
        // Es sollte dieselbe ID sein (1L)
        assertEquals(1L, result.getId());
        verify(saalRepository, times(1)).save(saal);
    }

    @Test
    void anlegenSaalOhneSitzreihen() {
        // Saal hat keine Sitzreihen -> getSitzreihen() == null
        saal.setSitzreihen(null);

        when(saalRepository.save(saal)).thenReturn(saal);

        Saal result = saalService.anlegenSaal(saal);
        // Kein Fehler -> normaler Ablauf
        assertNotNull(result);
        // Sitzreihen = null, also kein Loop
        assertNull(result.getSitzreihen());
        verify(saalRepository, times(1)).save(saal);
    }
}
