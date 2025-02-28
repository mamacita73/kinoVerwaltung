package com.kino.service;

import kinoVerwaltung.impl.BuchungImpl;
import kinoVerwaltung.impl.ReservierungImpl;
import kinoVerwaltung.impl.VorstellungImpl;

import java.util.Objects;
import java.util.Random;

public class TicketsBuchenService {

    /**
     * Bucht und bezahlt die reservierten Tickets
     */
    public void reservierungBuchen(ReservierungImpl reservierung) {

        Random random = new Random();
        BuchungImpl buchung = new BuchungImpl();

        double sitzPreis;

        if (Objects.equals(reservierung.getSitzplaetze().getFirst().getSitzkategorie().toString(), "PARKETT")) {
            sitzPreis = 1.0;
        } else if (Objects.equals(reservierung.getSitzplaetze().getFirst().getSitzkategorie().toString(), "LOGE")) {
            sitzPreis = 1.2;
        } else {
            sitzPreis = 1.5;

        }

        buchung.setBuchungsnummer(String.valueOf(random.nextInt(1000000, 9999999)));
        buchung.setPreis(reservierung.getTicketAnzahl() * (reservierung.getVorstellung().getPreis() * sitzPreis));

    }

    /**
     * Bucht und bezahlt die Tickets
     */
    public void buchen(VorstellungImpl vorstellung, int anzahlTickets) {

        Random random = new Random();
        BuchungImpl buchung = new BuchungImpl();

        double sitzPreis;

        if (Objects.equals(buchung.getSitzplaetze().getFirst().getSitzkategorie().toString(), "PARKETT")) {
            sitzPreis = 1.0;
        } else if (Objects.equals(buchung.getSitzplaetze().getFirst().getSitzkategorie().toString(), "LOGE")) {
            sitzPreis = 1.2;
        } else {
            sitzPreis = 1.5;

        }
        buchung.setTicketAnzahl(anzahlTickets);
        buchung.setPreis((vorstellung.getPreis() * sitzPreis) * anzahlTickets);
        buchung.setBuchungsnummer(String.valueOf(random.nextInt(1000000, 9999999)));



    }

}
