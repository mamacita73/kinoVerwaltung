package com.kino.service;

import com.kino.entity.Kino;
import com.kino.entity.Saal;
import com.kino.repository.KinoRepository;
import com.kino.repository.SaalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KinoServiceTest {

    @Mock
    private KinoRepository kinoRepository;

    @Mock
    private SaalRepository saalRepository;

    @InjectMocks
    private KinoService kinoService;

    private Kino kino;

    @BeforeEach
    void setUpTestData() {
        kino = new Kino();
        kino.setId(1L);
        kino.setName("TestKino");
    }

    // --- Tests für getAllKinos() ---

    @Test
    void getAllKinos() {
        // Wir simulieren, dass das Repository 2 Kino-Objekte zurückgibt
        when(kinoRepository.findAll()).thenReturn(Arrays.asList(new Kino(), new Kino()));

        List<Kino> result = kinoService.getAllKinos();
        assertEquals(2, result.size());
        // Prüfen, ob findAll() genau 1x aufgerufen wurde
        verify(kinoRepository, times(1)).findAll();
    }

    // --- Tests für getKinoById() ---

    @Test
    void getKinoByIdFound() {
        when(kinoRepository.findById(1L)).thenReturn(Optional.of(kino));
        Optional<Kino> result = kinoService.getKinoById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("TestKino", result.get().getName());
        verify(kinoRepository, times(1)).findById(1L);
    }

    @Test
    void getKinoByIdNotFound() {
        when(kinoRepository.findById(999L)).thenReturn(Optional.empty());
        Optional<Kino> result = kinoService.getKinoById(999L);

        assertFalse(result.isPresent());
        verify(kinoRepository, times(1)).findById(999L);
    }

    // --- Tests für saveKino() ---

    @Test
    void saveKino() {
        when(kinoRepository.save(kino)).thenReturn(kino);
        Kino result = kinoService.saveKino(kino);

        assertNotNull(result);
        assertEquals("TestKino", result.getName());
        verify(kinoRepository, times(1)).save(kino);
    }

    // --- Tests für updateKino() ---

    @Test
    void updateKinoFound() {
        // Simulieren, dass das Kino mit ID=1 existiert
        when(kinoRepository.existsById(1L)).thenReturn(true);
        when(kinoRepository.save(any(Kino.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Neues Kino-Objekt mit geändertem Namen
        Kino updateData = new Kino();
        updateData.setName("UpdatedKino");

        Kino result = kinoService.updateKino(1L, updateData);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("UpdatedKino", result.getName());
        verify(kinoRepository, times(1)).existsById(1L);
        verify(kinoRepository, times(1)).save(any(Kino.class));
    }

    @Test
    void updateKinoNotFound() {
        // Simulieren, dass das Kino mit ID=2 nicht existiert
        when(kinoRepository.existsById(2L)).thenReturn(false);

        Kino updateData = new Kino();
        updateData.setName("NewName");

        Kino result = kinoService.updateKino(2L, updateData);
        assertNull(result);
        verify(kinoRepository, times(1)).existsById(2L);
        // save(...) wird nicht aufgerufen, weil das Kino nicht existiert
        verify(kinoRepository, never()).save(any(Kino.class));
    }

    // --- Tests für deleteKino() ---

    @Test
    void deleteKino() {
        doNothing().when(kinoRepository).deleteById(1L);
        kinoService.deleteKino(1L);
        verify(kinoRepository, times(1)).deleteById(1L);
    }

    // --- Tests für getSaeleByKino() ---

    @Test
    void getSaeleByKino() {
        Long kinoId = 1L;
        List<Saal> saele = Arrays.asList(new Saal(), new Saal());
        when(saalRepository.findByKinoId(kinoId)).thenReturn(saele);

        List<Saal> result = kinoService.getSaeleByKino(kinoId);
        assertEquals(2, result.size());
        verify(saalRepository, times(1)).findByKinoId(kinoId);
    }
}
