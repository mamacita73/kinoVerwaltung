package com.kino.service;

import com.kino.entity.Saal;
import com.kino.entity.Vorstellung;
import com.kino.repository.SaalRepository;
import com.kino.repository.VorstellungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
public class VorstellungService {

    @Autowired
    private VorstellungRepository vorstellungRepository;

    @Autowired
    private SaalRepository saalRepository;

    public List<Vorstellung> getAllVorstellungen() {
        return vorstellungRepository.findAll();
    }

    public List<Vorstellung> getVorstellungByFilmTitel(String filmTitel) {
        return vorstellungRepository.findByFilmTitel(filmTitel);
    }

    public Vorstellung saveVorstellung(Vorstellung vorstellung) {
        return vorstellungRepository.save(vorstellung);
    }


    @Transactional
    public Vorstellung anlegenVorstellung(String filmTitel, String startzeitStr, int dauerMinuten, Long saalId) {
        // Saal laden
        Saal saal = saalRepository.findById(saalId)
                .orElseThrow(() -> new RuntimeException("Saal mit ID " + saalId + " nicht gefunden!"));

        // Neue Vorstellung erstellen und Felder setzen
        Vorstellung vorstellung = new Vorstellung();
        vorstellung.setFilmTitel(filmTitel);
        // Konvertiere den String (z.B. "16:00") in LocalTime
        vorstellung.setStartzeit(LocalTime.parse(startzeitStr));
        vorstellung.setDauerMinuten(dauerMinuten);
        vorstellung.setSaal(saal);

        // Persistieren und zurückgeben
        Vorstellung saved = vorstellungRepository.save(vorstellung);
        System.out.println("=== [VorstellungService] Vorstellung gespeichert mit ID " + saved.getId() + " ===");
        return saved;
    }
}
