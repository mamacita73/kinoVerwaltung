package com.kino.service;

import kinoVerwaltung.Saal;
import kinoVerwaltung.Sitzkategorie;
import kinoVerwaltung.Sitzstatus;
import kinoVerwaltung.impl.SaalImpl;
import kinoVerwaltung.impl.SitzplatzImpl;
import kinoVerwaltung.impl.SitzreiheImpl;

public class SaalAnlegenService {

    private int counter = 1;
    private int reihenCounter = 1;

    /**
     * Erstellt einen neuen Saal mit einem Namen
     */
    public void saalAnlegen() {

        SaalImpl saal = new SaalImpl();
        saal.setName("Saal " + counter);
        counter++;

    }

    /**
     * Hinterlegt die Anzahl der Reihen in einem Saal
     * @param saal
     * @param anzahlReihen
     */
    public void reihenAnlegen(Saal saal, int anzahlReihen) {

        if(anzahlReihen >= 0) {
            saal.setAnzahlReihen(anzahlReihen);
            reihenCounter = anzahlReihen;
        } else {
            throw new IllegalArgumentException("Anzahl der Reihen muss größer oder gleich 0 sein!");
        }


    }

    /**
     * Hinterlegt die Anzahl der Sitze und die Kategorie in einer Reihe
     * @param saal
     * @param anzahlSitze
     * @param kategorie
     */
    public void sitzeAnlegen(Saal saal, int anzahlSitze, Sitzkategorie kategorie) {

        if (saal != null) {
            SitzreiheImpl sitzreihe = new SitzreiheImpl();
            sitzreihe.setAnzahlPlaetze(anzahlSitze);
            sitzreihe.setNummer(reihenCounter);
            for (int i = 1; i <= anzahlSitze; i++) {
                SitzplatzImpl sitzplatz = new SitzplatzImpl();
                sitzplatz.setNummer(i);
                sitzplatz.setSitzkategorie(kategorie);
                sitzplatz.setSitzstatus(Sitzstatus.FREI);
                sitzplatz.setNummer(reihenCounter);
            }
            reihenCounter--;
        } else {
            throw new NullPointerException("Saal darf nicht null sein!");
        }

    }

}
