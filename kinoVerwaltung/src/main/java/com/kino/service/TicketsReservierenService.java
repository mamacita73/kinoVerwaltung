package com.kino.service;

import kinoVerwaltung.Sitzkategorie;
import kinoVerwaltung.Sitzstatus;
import kinoVerwaltung.Status;
import kinoVerwaltung.Vorstellung;
import kinoVerwaltung.impl.ReservierungImpl;
import kinoVerwaltung.impl.VorstellungImpl;

import java.util.Random;

public class TicketsReservierenService {

    /**
     * Reserviert Tickets für eine Vorstellung
     * @param film
     * @param kategorie
     * @param anzahlTickets
     * @return
     */
    public int reserviereTickets(VorstellungImpl film, Sitzkategorie kategorie, int anzahlTickets) {

        Random random = new Random();

        ReservierungImpl reservierung = new ReservierungImpl();
        reservierung.setStatus(Status.OFFEN);
        reservierung.setReservierungsnummer(String.valueOf(random.nextInt(1000000, 9999999)));
        reservierung.setVorstellung(film);
        reservierung.setTicketAnzahl(anzahlTickets);

        // Wenn genügend Sitzplätze in der Kategorie vorhanden sind, dann werden diese reserviert
        if (film.getFreiePlätze().size()>= anzahlTickets) {
            for (int i = 0; i < film.getFreiePlätze().size(); i++) {
                reservierung.getSitzplaetze().add(film.getFreiePlätze().get(i));
                film.getFreiePlätze().get(i).setSitzstatus(Sitzstatus.RESERVIERT);
            }
            return film.getFreiePlätze().size();
        } else {
            return 0;
        }

    }



}
