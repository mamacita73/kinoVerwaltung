package com.kino.service;

import com.kino.dto.ReservierungDTO;
import com.kino.entity.Benutzer;
import com.kino.entity.Reservierung;
import com.kino.entity.Vorstellung;
import com.kino.repository.BenutzerRepository;
import com.kino.repository.ReservierungRepository;
import com.kino.repository.VorstellungRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service für Reservierung
@Service
public class ReservierungService {
    @Autowired
    private ReservierungRepository reservierungRepository;

    @Autowired
    private VorstellungRepository vorstellungRepository;

    @Autowired
    private BenutzerRepository benutzerRepository;


    // Beispiel: Erstelle Reservierung
    @Transactional
    public Reservierung createReservierung(ReservierungDTO dto) {
        // Hier solltest du idealerweise die Logik einbauen, um Sitze zu reservieren (inkl. Zwischentabelle)
        // Dies kann aber auch in der CommandFactory erfolgen. Hier ein einfacher Beispielcode:

        // Vorstellung laden
        Vorstellung vorstellung = vorstellungRepository.findById(dto.getVorstellungId())
                .orElseThrow(() -> new RuntimeException("Vorstellung nicht gefunden"));

        //Benutzer
        Benutzer benutzer = benutzerRepository.findByEmail(dto.getKundenEmail())
                .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden mit E-Mail=" + dto.getKundenEmail()));

        Reservierung reservierung = new Reservierung();
        // Reservierungsnummer generieren (5-stellig)
        String resNr = String.format("%05d", new java.util.Random().nextInt(100000));
        reservierung.setReservierungsnummer(resNr);
        reservierung.setDatum(dto.getDatum());
        reservierung.setStatus(dto.getStatus());
        reservierung.setBenutzer(benutzer);
        reservierung.setVorstellung(vorstellung);

        // Hier könntest du die ReservierungSitz-Einträge setzen

        Reservierung saved = reservierungRepository.save(reservierung);
        System.out.println("=== [ReservierungService] Reservierung gespeichert, ID="
                + saved.getId() + ", Nr=" + saved.getReservierungsnummer());

        return saved;
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