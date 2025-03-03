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
import java.util.*;

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

    // Vorstellung anlegen mit Zeitprüfung
    @Transactional
    public Vorstellung anlegenVorstellung(String filmTitel, String startzeitStr, int dauerMinuten, Long saalId) {
        // Saal abrufen
        Saal saal = saalRepository.findById(saalId)
                .orElseThrow(() -> new RuntimeException("Saal mit ID " + saalId + " nicht gefunden!"));

        // Startzeit umwandeln
        LocalTime newStart = LocalTime.parse(startzeitStr);
        LocalTime newEnd = newStart.plusMinutes(dauerMinuten);

        // Überlappung mit existierenden Vorstellungen prüfen
        List<Vorstellung> existing = vorstellungRepository.findBySaalId(saalId);
        for (Vorstellung v : existing) {
            LocalTime existingStart = v.getStartzeit();
            LocalTime existingEnd = existingStart.plusMinutes(v.getDauerMinuten());

            boolean overlap = newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart);
            if (overlap) {
                throw new RuntimeException("Zeitüberschneidung! Es gibt schon eine Vorstellung von " +
                        existingStart + " bis " + existingEnd + " in Saal " + saal.getName());
            }
        }

        // Neue Vorstellung erstellen
        Vorstellung vorstellung = new Vorstellung();
        vorstellung.setFilmTitel(filmTitel);
        vorstellung.setStartzeit(newStart);
        vorstellung.setDauerMinuten(dauerMinuten);
        vorstellung.setSaal(saal);

        // 💾 Speichern
        return vorstellungRepository.save(vorstellung);
    }

    // Mehrere Vorstellungen auf einmal anlegen
    @Transactional
    public List<Vorstellung> anlegenMehrereVorstellungen(MultiVorstellungenDTO dto) {
        if (dto.getSaalIds().size() != dto.getStartzeiten().size()) {
            throw new RuntimeException("Fehler: Anzahl der Säle und Startzeiten muss gleich sein!");
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

    // Verfügbare Sitzplätze berechnen
    @Transactional
    public Map<String, Integer> berechneVerfuegbarkeit(Long vorstellungId) {
        // 🏛 Vorstellung abrufen
        Vorstellung v = vorstellungRepository
                .findByIdFetchSaalAndSitzreihen(vorstellungId)
                .orElseThrow(() -> new RuntimeException("Vorstellung nicht gefunden!"));

        Saal saal = v.getSaal();
        if (saal == null) {
            throw new RuntimeException("Fehler: Saal nicht geladen. Überprüfen Sie Lazy-Loading!");
        }

        // Verfügbarkeitskarte erstellen
        Map<String, Integer> availability = new HashMap<>();
        availability.put("PARKETT", 0);
        availability.put("LOGE", 0);
        availability.put("LOGE_SERVICE", 0);

        // Sitzplatzprüfung (Lazy-Loading umgehen)
        saal.getSitzreihen().forEach(sr -> sr.getSitze().forEach(sitz -> {
            if (sitz.getStatus().equals(Sitzstatus.FREI)) {
                String cat = sitz.getKategorie().name();
                availability.put(cat, availability.getOrDefault(cat, 0) + 1);
            }
        }));

        return availability;
    }
}
