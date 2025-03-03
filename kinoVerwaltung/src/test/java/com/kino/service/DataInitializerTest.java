package com.kino.service;

import com.kino.entity.Kino;
import com.kino.repository.KinoRepository;
import com.kino.repository.SaalRepository;
import com.kino.repository.VorstellungRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.CommandLineRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataInitializerTest {

    @Mock
    private KinoRepository kinoRepository;

    @Mock
    private SaalRepository saalRepository;

    @Mock
    private VorstellungRepository vorstellungRepository;

    // Wir instanziieren den DataInitializer, um die Bean-Methode initData() direkt aufzurufen.
    private DataInitializer dataInitializer;

    @BeforeEach
    void setUp() {
        dataInitializer = new DataInitializer();
    }

    @Test
    void initData_KinoNichtVorhanden() throws Exception {
        // Simuliere, dass ein Kino mit ID 1 nicht existiert
        when(kinoRepository.existsById(1L)).thenReturn(false);
        // Für den zweiten Block, der Vorstellungen prüft, geben wir hier einen beliebigen Wert zurück.
        when(vorstellungRepository.count()).thenReturn(0L);

        // Hole den CommandLineRunner aus der initData()-Methode
        CommandLineRunner runner = dataInitializer.initData(kinoRepository, saalRepository, vorstellungRepository);
        runner.run();

        // Es muss ein neues Kino mit den vorgegebenen Werten angelegt werden.
        verify(kinoRepository, times(1)).save(argThat((Kino k) ->
                k.getId().equals(1L)
                        && "Kino an der FHDW".equals(k.getName())
                        && "Freundallee 15, Hannover".equals(k.getAdresse())
        ));
    }

    @Test
    void initData_KinoVorhanden() throws Exception {
        // Simuliere, dass ein Kino mit ID 1 bereits existiert
        when(kinoRepository.existsById(1L)).thenReturn(true);
        when(vorstellungRepository.count()).thenReturn(5L); // beliebiger Wert

        CommandLineRunner runner = dataInitializer.initData(kinoRepository, saalRepository, vorstellungRepository);
        runner.run();

        // Es wird in diesem Fall kein neues Kino gespeichert
        verify(kinoRepository, never()).save(any(Kino.class));
    }
}
