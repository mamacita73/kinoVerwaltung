package com.kino.messaging;

import com.kino.entity.Benutzer;
import com.kino.repository.BenutzerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseCommand {
    private final BenutzerRepository benutzerRepository;

    public DatabaseCommand(BenutzerRepository benutzerRepository) {
        this.benutzerRepository = benutzerRepository;
    }

    /**
     * Sucht einen Benutzer anhand der E-Mail-Adresse und gibt ihn zurück.
     *
     * @param email Die E-Mail-Adresse des Benutzers
     * @return Optional<Benutzer> falls vorhanden, sonst leer
     */
    public Optional<Benutzer> findUserByEmail(String email) {
        return benutzerRepository.findByEmail(email);
    }
}
