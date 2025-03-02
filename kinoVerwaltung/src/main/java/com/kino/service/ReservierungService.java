package com.kino.service;

import com.kino.dto.ReservierungDTO;
import com.kino.entity.*;
import com.kino.repository.BenutzerRepository;
import com.kino.repository.ReservierungRepository;
import com.kino.repository.SaalRepository;
import com.kino.repository.VorstellungRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    // Beispiel: Erstelle Reservierung
    @Transactional
    public Reservierung reservierungAnlegen(Long vorstellungId, String kategorie, int anzahl,
                                            String kundenEmail, String datum, String status) {

        // 1) Vorstellung laden
        Vorstellung vorstellung = vorstellungRepository.findById(vorstellungId)
                .orElseThrow(() -> new RuntimeException(
                        "Vorstellung nicht gefunden: ID=" + vorstellungId));

        // 2) Saal ID ermitteln und Saal neu laden, damit Sitzreihen lazy-Loading funktioniert
        Long saalId = vorstellung.getSaal().getId();
        Saal realSaal = saalRepository.findById(saalId)
                .orElseThrow(() -> new RuntimeException("Saal mit ID=" + saalId + " nicht gefunden!"));

        // 3) Sitzplatz-Prüfung
        int seatsFound = 0;
        List<Sitz> reservierteSitze = new ArrayList<>();

        for (Sitzreihe sr : realSaal.getSitzreihen()) {
            for (Sitz sitz : sr.getSitze()) {
                if (sitz.getKategorie().name().equals(kategorie)
                        && sitz.getStatus() == Sitzstatus.FREI) {

                    sitz.setStatus(Sitzstatus.RESERVIERT);
                    reservierteSitze.add(sitz);
                    seatsFound++;
                    if (seatsFound == anzahl) break;
                }
            }
            if (seatsFound == anzahl) break;
        }

        if (seatsFound < anzahl) {
            // Zu wenig freie Plätze -> rückgängig machen
            for (Sitz s : reservierteSitze) {
                s.setStatus(Sitzstatus.FREI);
            }
            throw new RuntimeException(
                    "Nicht genug freie Plätze in Kategorie " + kategorie);
        }

        // 4) ReservierungSitz-Einträge anlegen
        List<ReservierungSitz> reservierungSitze = new ArrayList<>();
        for (Sitz s : reservierteSitze) {
            ReservierungSitz rs = new ReservierungSitz();
            rs.setSitz(s);
            reservierungSitze.add(rs);
        }

        // 5) Benutzer laden
        Benutzer benutzer = benutzerRepository.findByEmail(kundenEmail)
                .orElseThrow(() -> new RuntimeException(
                        "Benutzer nicht gefunden: email=" + kundenEmail));

        // 6) Neue Reservierung anlegen
        Reservierung reservierung = new Reservierung();
        String fiveDigitNumber = String.format("%05d", new Random().nextInt(100000));
        reservierung.setReservierungsnummer(fiveDigitNumber);
        reservierung.setDatum(datum);
        reservierung.setStatus(status);
        reservierung.setBenutzer(benutzer);
        reservierung.setVorstellung(vorstellung);

        // 7) Referenzen setzen
        for (ReservierungSitz rs : reservierungSitze) {
            rs.setReservierung(reservierung);
        }
        reservierung.setReservierungSitze(reservierungSitze);

        // 8) Speichern
        Reservierung savedRes = reservierungRepository.save(reservierung);
        System.out.println("=== [ReservierungLogicService] Reservierung gespeichert, ID="
                + savedRes.getId() + ", Nr=" + savedRes.getReservierungsnummer());

        return savedRes;
    }

    public Reservierung getReservierungById(Long id) {
        return reservierungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservierung nicht gefunden"));
    }

    public List<Reservierung> getReservierungenByBenutzerId(Long benutzerId) {
        return reservierungRepository.findByBenutzerId(benutzerId);
    }

    @Transactional
    public void cancelReservierung(Long id) {
        Reservierung r = getReservierungById(id);
        // Hier Logik, um auch reservierte Sitze freizugeben, z. B.:
        // for (ReservierungSitz rs : r.getReservierungSitze()) {
        //     rs.getSitz().setStatus(Sitzstatus.FREI);
        // }
        r.setStatus("STORNIERT");
        reservierungRepository.save(r);
    }
}