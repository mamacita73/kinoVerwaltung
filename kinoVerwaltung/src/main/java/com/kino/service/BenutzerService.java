package com.kino.service;

import com.kino.entity.Benutzer;
import com.kino.repository.BenutzerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * für die KOmmunikation mit der DB
 */


@Service
public class BenutzerService {
    private final BenutzerRepository benutzerRepository;

    public BenutzerService(BenutzerRepository benutzerRepository) {
        this.benutzerRepository = benutzerRepository;
    }

    public List<Benutzer> getAlleBenutzer() {

        return benutzerRepository.findAll();
    }

    // Benutzer mit email finden
    public Benutzer findeBenutzerNachEmail(String email) {
        Optional<Benutzer> benutzer = benutzerRepository.findByEmail(email);
        return benutzer.orElse(null); // Falls Benutzer nicht gefunden, gib null zurück
    }

    //Benutzer neu speichern
    public Benutzer speichereBenutzer(Benutzer benutzer) {
        // Falls Benutzer noch keine Rolle hat oder nicht explizit als Admin gesetzt wurde
        if (benutzer.getRolle() == null || benutzer.getRolle().isEmpty()) {
            if ("admin@admin.com".equals(benutzer.getEmail())) {
                benutzer.setRolle("Admin");
            } else {
                benutzer.setRolle("Kunde");
            }
        }
        return benutzerRepository.save(benutzer);
    }
}