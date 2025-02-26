package com.kino.service;

import kinoVerwaltung.impl.VorstellungImpl;

public class FilmDashboardService {

    /**
     * Gibt die Einnahmen eines Films zurück
     * @param film
     * @return Einnahmen des Films
     */
    public double getFilmeinnahmen(VorstellungImpl film) {

        return film.berechneEinnahmen();

    }

}
