package com.kino.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kinoVerwaltung.Saal;
import kinoVerwaltung.Sitzkategorie;
import kinoVerwaltung.impl.SaalImpl;

class SaalAnlegenServiceTest {

    private SaalAnlegenService service;

    @BeforeEach
    void setUp() {
        service = new SaalAnlegenService();
    }

    // --- Tests für saalAnlegen() ---

    @Test
    void saalAnlegenNormalFall() throws Exception {
        // Zugriff auf das private Feld "counter" per Reflection
        Field counterField = SaalAnlegenService.class.getDeclaredField("counter");
        counterField.setAccessible(true);

        // Vor dem Aufruf sollte der counter den Wert 1 haben
        assertEquals(1, counterField.getInt(service));

        // Erster Aufruf von saalAnlegen()
        service.saalAnlegen();

        // Nach dem Aufruf wird counter erhöht
        assertEquals(2, counterField.getInt(service));

        // Zweiter Aufruf von saalAnlegen()
        service.saalAnlegen();
        // Counter sollte jetzt 3 sein
        assertEquals(3, counterField.getInt(service));
    }

    // --- Tests für reihenAnlegen() ---

    @Test
    void reihenAnlegenNormalFall() throws Exception {
        // Erzeugen eines Dummy-Saals
        Saal saal = new SaalImpl();

        // Zugriff auf das private Feld "reihenCounter"
        Field reihenCounterField = SaalAnlegenService.class.getDeclaredField("reihenCounter");
        reihenCounterField.setAccessible(true);

        // Zu Beginn sollte reihenCounter 1 sein
        assertEquals(1, reihenCounterField.getInt(service));

        // Reihen anlegen mit 5 Reihen
        service.reihenAnlegen(saal, 5);

        // Der Saal sollte nun 5 Reihen besitzen
        assertEquals(5, ((SaalImpl) saal).getAnzahlReihen());

        // Das interne Feld reihenCounter wurde auf 5 gesetzt
        assertEquals(5, reihenCounterField.getInt(service));
    }

    @Test
    void reihenAnlegenZero() throws Exception {
        Saal saal = new SaalImpl();
        service.reihenAnlegen(saal, 0);

        // Erwartet: 0 Reihen
        assertEquals(0, ((SaalImpl) saal).getAnzahlReihen());

        // Interner reihenCounter sollte 0 sein
        Field reihenCounterField = SaalAnlegenService.class.getDeclaredField("reihenCounter");
        reihenCounterField.setAccessible(true);
        assertEquals(0, reihenCounterField.getInt(service));
    }

    @Test
    void reihenAnlegenNegative() throws Exception {
        Saal saal = new SaalImpl();

        // Erwartet: Es wird eine Exception geworfen
        assertThrows(IllegalArgumentException.class, () -> {
            service.reihenAnlegen(saal, -3);
        });

    }

    @Test
    void reihenAnlegenWithNullSaal() {
        // Übergabe eines null-Saals sollte eine NullPointerException auslösen
        assertThrows(NullPointerException.class, () -> {
            service.reihenAnlegen(null, 5);
        });
    }

    // --- Tests für sitzeAnlegen() ---

    @Test
    void sitzeAnlegenNormalFall() throws Exception {
        Saal dummySaal = new SaalImpl();

        // Initialisieren des internen reihenCounters über reihenAnlegen
        service.reihenAnlegen(dummySaal, 3);

        Field reihenCounterField = SaalAnlegenService.class.getDeclaredField("reihenCounter");
        reihenCounterField.setAccessible(true);

        // Vor Aufruf von sitzeAnlegen sollte reihenCounter 3 sein
        assertEquals(3, reihenCounterField.getInt(service));

        // Aufruf von sitzeAnlegen mit 4 Sitzen und einer definierten Kategorie
        Sitzkategorie kategorie = Sitzkategorie.PARKETT;
        service.sitzeAnlegen(dummySaal, 4, kategorie);

        // Nach dem Aufruf wird der reihenCounter um 1 dekrementiert (3 -> 2)
        assertEquals(2, reihenCounterField.getInt(service));
    }

    @Test
    void sitzeAnlegenZero() throws Exception {
        Saal dummySaal = new SaalImpl();

        // Initialisieren des internen reihenCounters
        service.reihenAnlegen(dummySaal, 2);

        Field reihenCounterField = SaalAnlegenService.class.getDeclaredField("reihenCounter");
        reihenCounterField.setAccessible(true);
        assertEquals(2, reihenCounterField.getInt(service));

        // Aufruf von sitzeAnlegen mit 0 Sitzen
        service.sitzeAnlegen(dummySaal, 0, Sitzkategorie.PARKETT);

        // Auch wenn keine Sitze angelegt werden, wird der reihenCounter dekrementiert (2 -> 1)
        assertEquals(1, reihenCounterField.getInt(service));
    }

    @Test
    void sitzeAnlegenNegative() throws Exception {
        Saal dummySaal = new SaalImpl();
        service.reihenAnlegen(dummySaal, 4);

        Field reihenCounterField = SaalAnlegenService.class.getDeclaredField("reihenCounter");
        reihenCounterField.setAccessible(true);
        assertEquals(4, reihenCounterField.getInt(service));

        // Aufruf von sitzeAnlegen mit negativer Sitzanzahl (-5)
        service.sitzeAnlegen(dummySaal, -5, Sitzkategorie.PARKETT);

        // Trotz negativer Sitzanzahl wird der reihenCounter dekrementiert (4 -> 3)
        assertEquals(3, reihenCounterField.getInt(service));
    }

    @Test
    void sitzeAnlegenWithNullSaal() {
        // Übergabe eines null-Saals sollte eine NullPointerException auslösen
        assertThrows(NullPointerException.class, () -> {
            service.sitzeAnlegen(null, 4, Sitzkategorie.PARKETT);
        });
    }

    @Test
    void sitzeAnlegenWithNullKategorie() throws Exception {
        Saal dummySaal = new SaalImpl();
        service.reihenAnlegen(dummySaal, 3);

        Field reihenCounterField = SaalAnlegenService.class.getDeclaredField("reihenCounter");
        reihenCounterField.setAccessible(true);
        assertEquals(3, reihenCounterField.getInt(service));

        // Aufruf von sitzeAnlegen mit null als Kategorie
        service.sitzeAnlegen(dummySaal, 4, null);

        // Auch wenn die Kategorie null ist, sollte der reihenCounter dekrementiert werden (3 -> 2)
        assertEquals(2, reihenCounterField.getInt(service));
    }
}
