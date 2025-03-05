package com.kino.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kino.entity.*;
import com.kino.repository.BenutzerRepository;
import com.kino.repository.BuchungRepository;
import com.kino.repository.ReservierungRepository;
import com.kino.repository.VorstellungRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BuchungService {

    @Autowired
    private BuchungRepository buchungRepository;
    @Autowired
    private ReservierungRepository reservierungRepository;
    @Autowired
    private VorstellungRepository vorstellungRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate; // Für Stats-Events
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BenutzerRepository benutzerRepository;

    @Transactional
    public Buchung buchungAnlegen(Long vorstellungId, String kategorie, int anzahl, String kundenEmail) {
        // Direkte Buchung: Sitzstatus von FREI -> GEBUCHT
        Vorstellung v = vorstellungRepository.findById(vorstellungId)
                .orElseThrow(() -> new RuntimeException("Vorstellung nicht gefunden"));

        // Sitzsuche analog zur Reservierung, nur dass wir sie direkt auf GEBUCHT setzen
        Saal saal = v.getSaal();
        if (!saal.isIstFreigegeben()) {
            throw new RuntimeException("Saal ist nicht freigegeben!");
        }

        List<Sitz> freieSitze = new ArrayList<>();
        for (Sitzreihe sr : saal.getSitzreihen()) {
            for (Sitz sitz : sr.getSitze()) {
                if (sitz.getStatus() == Sitzstatus.FREI &&
                        sitz.getKategorie().name().equalsIgnoreCase(kategorie)) {
                    freieSitze.add(sitz);
                    if (freieSitze.size() == anzahl) break;
                }
            }
            if (freieSitze.size() == anzahl) break;
        }
        if (freieSitze.size() < anzahl) {
            throw new RuntimeException("Nicht genügend freie Plätze in " + kategorie);
        }

        int totalPrice = 0;
        List<BuchungSitz> bsList = new ArrayList<>();
        for (Sitz sitz : freieSitze) {
            sitz.setStatus(Sitzstatus.GEBUCHT);
            int price = calculateSeatPrice(sitz.getKategorie());
            totalPrice += price;

            BuchungSitz bs = new BuchungSitz();
            bs.setSitz(sitz);
            bsList.add(bs);
        }


        Benutzer benutzer = benutzerRepository.findByEmail(kundenEmail)
                .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden!"));


        Buchung buchung = new Buchung();
        buchung.setBenutzer(benutzer);
        buchung.setKundenEmail(kundenEmail);
        buchung.setStatus("GEBUCHT");
        buchung.setSumme(totalPrice);
        buchung.setBuchungsnummer(generateBuchungsnummer());
        buchung.setVorstellung(v);
        for (BuchungSitz bs : bsList) {
            bs.setBuchung(buchung);
        }
        buchung.setBuchungSitze(bsList);

        Buchung saved = buchungRepository.save(buchung);
        sendStatsEvent(saved);
        return saved;
    }

    /**
     * Wandelt eine vorhandene Reservierung in eine Buchung um (RESERVIERT -> GEBUCHT).
     */
    @Transactional
    public Buchung reservierungZuBuchung(Long reservierungId) {
        // Reservierung laden
        Reservierung res = reservierungRepository.findById(reservierungId)
                .orElseThrow(() -> new RuntimeException("Reservierung nicht gefunden!"));

        if (!"RESERVIERT".equalsIgnoreCase(res.getStatus())) {
            throw new RuntimeException("Reservierung ist nicht im Status RESERVIERT!");
        }

        // Alle zugehörigen Sitze prüfen und in einer Liste sammeln
        List<Sitz> sitze = new ArrayList<>();
        for (ReservierungSitz rs : res.getReservierungSitze()) {
            Sitz s = rs.getSitz();
            if (s.getStatus() != Sitzstatus.RESERVIERT) {
                throw new RuntimeException("Mindestens ein reservierter Sitz ist nicht mehr RESERVIERT!");
            }
            sitze.add(s);
        }

        // Sitzstatus auf GEBUCHT setzen und Gesamtpreis berechnen
        int totalPrice = 0;
        List<BuchungSitz> buchungSitze = new ArrayList<>();
        for (Sitz s : sitze) {
            s.setStatus(Sitzstatus.GEBUCHT);
            int seatPrice = calculateSeatPrice(s.getKategorie());
            totalPrice += seatPrice;

            BuchungSitz bs = new BuchungSitz();
            bs.setSitz(s);
            buchungSitze.add(bs);
        }

        // Neue Buchung anlegen (ohne Referenz auf die Reservierung)
        Buchung buchung = new Buchung();
        buchung.setStatus("GEBUCHT");
        buchung.setKundenEmail(res.getKundenEmail());
        buchung.setSumme(totalPrice);

        // Buchungsnummer: Reservierungsnummer + 1 (numerisch interpretiert)
        try {
            long resNr = Long.parseLong(res.getReservierungsnummer());
            buchung.setBuchungsnummer(String.valueOf(resNr + 1));
        } catch (NumberFormatException e) {
            throw new RuntimeException("Reservierungsnummer hat kein gültiges Format!");
        }

        buchung.setVorstellung(res.getVorstellung());

        // WICHTIG: Den Benutzer setzen (aus der ReservierungskundenEmail)
        Benutzer benutzer = benutzerRepository.findByEmail(res.getKundenEmail())
                .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden!"));
        buchung.setBenutzer(benutzer);

        for (BuchungSitz bs : buchungSitze) {
            bs.setBuchung(buchung);
        }
        buchung.setBuchungSitze(buchungSitze);

        Buchung saved = buchungRepository.save(buchung);

        // 5) Reservierung löschen, damit sie nicht mehr angezeigt wird
        res.getReservierungSitze().clear();
        reservierungRepository.delete(res);

        sendStatsEvent(saved);
        return saved;
    }





    public int calculateSeatPrice(Sitzkategorie kat) {
        int base = 7;
        switch (kat) {
            case PARKETT: return base + 1;
            case LOGE: return base + 3;
            case LOGE_SERVICE: return base + 5;
            default: return base;
        }
    }

    private String generateBuchungsnummer() {
        int randomNum = 10000 + new Random().nextInt(90000);
        return String.valueOf(randomNum);
    }

    /**
     * Sendet ein Event an RabbitMQ, um die Statistik in MongoDB zu aktualisieren.
     */
    private void sendStatsEvent(Buchung buchung) {
        try {
            Map<String, Object> event = new HashMap<>();
            event.put("vorstellungId", buchung.getVorstellung().getId());
            event.put("filmTitel", buchung.getVorstellung().getFilmTitel());
            event.put("summe", buchung.getSumme());
            event.put("buchungsnummer", buchung.getBuchungsnummer());

            String json = objectMapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend("bookingStatsQueue", json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
