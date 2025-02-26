package com.kino.service;

import kinoVerwaltung.Reservierung;
import kinoVerwaltung.impl.BuchungImpl;
import kinoVerwaltung.impl.ReservierungImpl;
import kinoVerwaltung.impl.VorstellungImpl;

import java.util.Random;

public class TicketsBuchenService {

    /**
     * Bucht und bezahlt die reservierten Tickets
     */
    public void reservierungBuchen(ReservierungImpl reservierung) {

        Random random = new Random();
        BuchungImpl buchung = new BuchungImpl();

        buchung.setBuchungsnummer(String.valueOf(random.nextInt(1000000, 9999999)));
        buchung.setPreis(reservierung.getTicketAnzahl() * reservierung.getVorstellung().getPreis());

    }

    /**
     * Bucht und bezahlt die Tickets
     */
    public void buchen(VorstellungImpl vorstellung, int anzahlTickets) {

        Random random = new Random();
        BuchungImpl buchung = new BuchungImpl();

        buchung.setTicketAnzahl(anzahlTickets);
        buchung.setPreis(vorstellung.getPreis() * anzahlTickets);
        buchung.setBuchungsnummer(String.valueOf(random.nextInt(1000000, 9999999)));


    }

}
