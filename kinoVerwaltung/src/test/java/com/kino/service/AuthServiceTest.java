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
class AuthServiceTest {

    @Mock
    private BenutzerRepository benutzerRepository;

    @InjectMocks
    private AuthService authService;

    private Benutzer benutzer;

    @BeforeEach
    void setUp() {
        benutzer = new Benutzer();
        benutzer.setId(1L);
        benutzer.setEmail("test@example.com");
        // Weitere Eigenschaften des Benutzers können hier gesetzt werden, z. B. Rolle
    }

    @Test
    void loginBenutzerGefunden() {
        // Simuliere, dass der Benutzer im Repository gefunden wird
        when(benutzerRepository.findByEmail("test@example.com")).thenReturn(Optional.of(benutzer));

        Optional<Benutzer> result = authService.login("test@example.com");

        assertTrue(result.isPresent());
        assertEquals("test@example.com", result.get().getEmail());
        verify(benutzerRepository, times(1)).findByEmail("test@example.com");
    }

    @Test
    void loginBenutzerNichtGefunden() {
        // Simuliere, dass kein Benutzer unter der angegebenen E-Mail existiert
        when(benutzerRepository.findByEmail("nichtgefunden@example.com")).thenReturn(Optional.empty());

        Optional<Benutzer> result = authService.login("nichtgefunden@example.com");

        assertFalse(result.isPresent());
        verify(benutzerRepository, times(1)).findByEmail("nichtgefunden@example.com");
    }

    @Test
    void registerBenutzer() {
        // Simuliere, dass beim Speichern der Benutzer zurückgegeben wird
        when(benutzerRepository.save(benutzer)).thenReturn(benutzer);

        Benutzer saved = authService.register(benutzer);

        assertNotNull(saved);
        assertEquals("test@example.com", saved.getEmail());
        verify(benutzerRepository, times(1)).save(benutzer);
    }
}
