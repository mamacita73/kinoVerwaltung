package com.kino.service;

import com.kino.dto.MultiVorstellungenDTO;
import com.kino.entity.Saal;
import com.kino.entity.Vorstellung;
import com.kino.repository.SaalRepository;
import com.kino.repository.VorstellungRepository;
import kinoVerwaltung.Sitzstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public List<Vorstellung> getVorstellungenBySaal(Long saalId) {
        return vorstellungRepository.findBySaalId(saalId);
    }

    public Vorstellung saveVorstellung(Vorstellung vorstellung) {
        return vorstellungRepository.save(vorstellung);
    }


    @Transactional
    public Vorstellung anlegenVorstellung(String filmTitel, String startzeitStr, int dauerMinuten, Long saalId) {
        // Saal laden
        Saal saal = saalRepository.findById(saalId)
                .orElseThrow(() -> new RuntimeException("Saal mit ID " + saalId + " nicht gefunden!"));

        //  Neue Zeiten bestimmen
        LocalTime newStart = LocalTime.parse(startzeitStr);        // z.B. "16:00" -> 16:00
        LocalTime newEnd = newStart.plusMinutes(dauerMinuten);     // z.B. 16:00 + 90 = 17:30

        //  Alle existierenden Vorstellungen in diesem Saal laden
        List<Vorstellung> existing = vorstellungRepository.findBySaalId(saalId);

        //  Zeit-Überlappung prüfen
        for (Vorstellung v : existing) {
            LocalTime existingStart = v.getStartzeit();
            LocalTime existingEnd = existingStart.plusMinutes(v.getDauerMinuten());

            // Prüfe, ob (newStart, newEnd) sich mit (existingStart, existingEnd) überschneidet:
            // Das ist der Fall, wenn newStart < existingEnd und newEnd > existingStart
            // (d.h. das Zeitintervall überlappt sich)
            boolean overlap = newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart);
            if (overlap) {
                throw new RuntimeException(
                        "Konflikt: In Saal " + saal.getName() +
                                " findet bereits eine Vorstellung (" + v.getFilmTitel() +
                                ") von " + existingStart + " bis " + existingEnd + " statt!"
                );
            }
        }

        //  Wenn kein Konflikt, Vorstellung anlegen
        Vorstellung vorstellung = new Vorstellung();
        vorstellung.setFilmTitel(filmTitel);
        vorstellung.setStartzeit(newStart);
        vorstellung.setDauerMinuten(dauerMinuten);
        vorstellung.setSaal(saal);

        Vorstellung saved = vorstellungRepository.save(vorstellung);
        System.out.println("=== [VorstellungService] Vorstellung gespeichert mit ID " + saved.getId() + " ===");
        return saved;
    }


    // Mehrfache Vorstellungen anlegen
    @Transactional
    public List<Vorstellung> anlegenMehrereVorstellungen(MultiVorstellungenDTO dto) {
        if (dto.getSaalIds().size() != dto.getStartzeiten().size()) {
            throw new RuntimeException("Anzahl Säle und Startzeiten muss übereinstimmen!");
        }

        List<Vorstellung> result = new ArrayList<>();
        for (int i = 0; i < dto.getSaalIds().size(); i++) {
            Long saalId = dto.getSaalIds().get(i);
            String startzeitStr = dto.getStartzeiten().get(i);

            Vorstellung v = anlegenVorstellung(
                    dto.getFilmTitel(),
                    startzeitStr,
                    dto.getDauerMinuten(),
                    saalId
            );
            result.add(v);
        }
        return result;
    }

    @Transactional
    public Map<String, Integer> berechneVerfuegbarkeit(Long vorstellungId) {
        // Angepasste Methode, die per Join Fetch alle Relationen lädt
        Vorstellung v = vorstellungRepository
                .findByIdFetchSaalAndSitzreihen(vorstellungId)
                .orElseThrow(() -> new RuntimeException("Vorstellung nicht gefunden"));

        Saal saal = v.getSaal(); // hier noch in aktiver Session
        Map<String, Integer> availability = new HashMap<>();
        availability.put("PARKETT", 0);
        availability.put("LOGE", 0);
        availability.put("LOGE_SERVICE", 0);

        // Jetzt können Sie sicher auf saal.getSitzreihen() zugreifen
        saal.getSitzreihen().forEach(sr -> {
            sr.getSitze().forEach(sitz -> {
                if (sitz.getStatus().equals(Sitzstatus.FREI)) {
                    String cat = sitz.getKategorie().name();
                    availability.put(cat, availability.get(cat) + 1);
                }
            });
        });

        return availability;
    }

}
