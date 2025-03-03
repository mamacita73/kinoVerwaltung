package com.kino.service;

import com.kino.entity.Benutzer;
import com.kino.repository.BenutzerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BenutzerServiceTest {

    @Mock
    private BenutzerRepository benutzerRepository;

    @InjectMocks
    private BenutzerService benutzerService;

    private Benutzer benutzer;

    @BeforeEach
    void setUp() {
        benutzer = new Benutzer();
        benutzer.setId(1L);
        benutzer.setEmail("test@example.com");
        // Setze ggf. weitere Eigenschaften des Benutzers
    }

    @Test
    void getBenutzerByEmailFound() {
        // Simuliere, dass ein Benutzer mit der gegebenen E-Mail gefunden wird
        when(benutzerRepository.findByEmail("test@example.com")).thenReturn(Optional.of(benutzer));

        Optional<Benutzer> result = benutzerService.getBenutzerByEmail("test@example.com");

        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
        verify(benutzerRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void getBenutzerByEmailNotFound() {
        // Simuliere, dass kein Benutzer mit der gegebenen E-Mail gefunden wird
        when(benutzerRepository.findByEmail("nichtgefunden@example.com")).thenReturn(Optional.empty());

        Optional<Benutzer> result = benutzerService.getBenutzerByEmail("nichtgefunden@example.com");

        assertFalse(result.isPresent());
        verify(benutzerRepository, times(1)).findByEmail("nichtgefunden@example.com");
    }

    @Test
    void saveBenutzer() {
        // Simuliere, dass der Benutzer beim Speichern zurückgegeben wird
        when(benutzerRepository.save(benutzer)).thenReturn(benutzer);

        Benutzer saved = benutzerService.saveBenutzer(benutzer);

        assertNotNull(saved);
        assertEquals("test@example.com", saved.getEmail());
        verify(benutzerRepository, times(1)).save(benutzer);
    }
}
