package com.kino.service;

import com.kino.dto.ReservierungDTO;
import com.kino.entity.*;
import com.kino.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

// Service für Reservierung
@Service
public class ReservierungService {
    @Autowired
    private ReservierungRepository reservierungRepository;

    @Autowired
    private VorstellungRepository vorstellungRepository;

    @Autowired
    private BenutzerRepository benutzerRepository;
    @Autowired
    private SaalRepository saalRepository;

    @Autowired
    private BuchungRepository buchungRepository;

    /**
     * Legt eine neue Reservierung an, prüft die Sitzverfügbarkeit und reserviert die benötigten Sitze.
     */
    @Transactional
    public Reservierung reservierungAnlegen(Long vorstellungId,
                                            String kategorie,
                                            int anzahl,
                                            String kundenEmail,
                                            String datum,
                                            String status) {
        // Laden der Vorstellung inkl. Saal, Sitzreihen und Sitze
        Vorstellung vorstellung = vorstellungRepository
                .findByIdFetchSaalAndSitzreihen(vorstellungId)
                .orElseThrow(() -> new RuntimeException("Vorstellung nicht gefunden"));

        // Berechne die Anzahl freier Sitze in der angefragten Kategorie
        int freiePlaetze = 0;
        for (Sitzreihe sr : vorstellung.getSaal().getSitzreihen()) {
            for (Sitz sitz : sr.getSitze()) {
                if (sitz.getKategorie().name().equalsIgnoreCase(kategorie) &&
                        sitz.getStatus() == Sitzstatus.FREI) {
                    freiePlaetze++;
                }
            }
        }

        if (freiePlaetze < anzahl) {
            throw new RuntimeException("Nicht genügend freie Plätze in Kategorie " + kategorie +
                    ". Verfügbar: " + freiePlaetze);
        }

        // Reserviere die benötigte Anzahl an Sitzen (setzt Status auf RESERVIERT)
        int reserviert = 0;
        outer:
        for (Sitzreihe sr : vorstellung.getSaal().getSitzreihen()) {
            for (Sitz sitz : sr.getSitze()) {
                if (sitz.getKategorie().name().equalsIgnoreCase(kategorie) &&
                        sitz.getStatus() == Sitzstatus.FREI) {
                    sitz.setStatus(Sitzstatus.RESERVIERT);
                    reserviert++;
                    if (reserviert == anzahl) {
                        break outer;
                    }
                }
            }
        }

        // Erstelle den Reservierungseintrag
        Reservierung res = new Reservierung();
        res.setKundenEmail(kundenEmail);
        res.setDatum(datum);
        res.setStatus(status);
        res.setVorstellungId(vorstellungId);

        // Generiere eine Reservierungsnummer (hier als Zufallszahl)
        Random rand = new Random();
        int randomNum = 10000 + rand.nextInt(90000);
        res.setReservierungsnummer(String.valueOf(randomNum));

        Reservierung saved = reservierungRepository.save(res);
        System.out.println("=== [ReservierungService] Reservierung gespeichert, ID=" + saved.getId() + " ===");
        return saved;
    }

    public Reservierung getReservierungById(Long id) {
        return reservierungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservierung nicht gefunden"));
    }

    public List<Reservierung> getReservierungenByBenutzerId(Long benutzerId) {
        return reservierungRepository.findByBenutzerId(benutzerId);
    }

    // Neue Methode: Alle Reservierungen eines Benutzers anhand der E-Mail abrufen
    public List<Reservierung> getReservierungenByEmail(String email) {
        Benutzer benutzer = benutzerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden mit E-Mail: " + email));
        return reservierungRepository.findByBenutzerId(benutzer.getId());
    }

    @Transactional
    public Buchung reservierungZuBuchung(String reservierungsnummer, String zahlweise) {
        // Reservierung finden
        Reservierung r = reservierungRepository.findByReservierungsnummer(reservierungsnummer)
                .orElseThrow(() -> new RuntimeException("Reservierung nicht gefunden mit Nr=" + reservierungsnummer));

        if (r.getStatus().equals("STORNIERT")) {
            throw new RuntimeException("Reservierung ist bereits storniert und kann nicht gebucht werden!");
        }
        // Neue Buchung anlegen
        Buchung buchung = new Buchung();
        buchung.setBuchungsnummer(/* generieren */ "B" + System.currentTimeMillis());
        buchung.setDatum(new Date());
        buchung.setBenutzer(r.getBenutzer());
        buchung.setStatus("GEBUCHT");
        // evtl. preis berechnen
        // Sitze übernehmen?
        // ...

        // Speichern
        Buchung saved = buchungRepository.save(buchung);

        // Reservierung auf "GEBUCHT" oder "ABGESCHLOSSEN" setzen, je nach Logik
        r.setStatus("GEBUCHT");
        reservierungRepository.save(r);

        return saved;
    }


    @Transactional
    public void cancelReservierung(Long id) {
        Reservierung r = getReservierungById(id);
        // Sitzbelegung freigeben
        r.setStatus("STORNIERT");
        reservierungRepository.save(r);
    }
}