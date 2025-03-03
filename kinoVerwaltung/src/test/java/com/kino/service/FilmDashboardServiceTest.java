package com.kino.service;

import kinoVerwaltung.impl.VorstellungImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilmDashboardServiceTest {

    private final FilmDashboardService service = new FilmDashboardService();

    @Test
    void getFilmeinnahmenNormalFall() {
        // Erstellen eines Mocks für VorstellungImpl
        VorstellungImpl filmMock = mock(VorstellungImpl.class);
        // Simuliere, dass die Methode berechneEinnahmen() 150.0 zurückgibt
        when(filmMock.berechneEinnahmen()).thenReturn(150.0);

        double einnahmen = service.getFilmeinnahmen(filmMock);
        assertEquals(150.0, einnahmen);
    }

    @Test
    void getFilmeinnahmenZero() {
        VorstellungImpl filmMock = mock(VorstellungImpl.class);
        // Simuliere, dass keine Einnahmen erzielt wurden
        when(filmMock.berechneEinnahmen()).thenReturn(0.0);

        double einnahmen = service.getFilmeinnahmen(filmMock);
        assertEquals(0.0, einnahmen);
    }

    @Test
    void getFilmeinnahmenMitNull() {
        // Übergabe eines null-Films sollte eine NullPointerException auslösen
        assertThrows(NullPointerException.class, () -> service.getFilmeinnahmen(null));
    }
}
