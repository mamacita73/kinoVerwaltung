package com.kino.repository;

import com.kino.entity.Benutzer;
import com.kino.entity.Rolle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Respository für Benutzer für DB-Zugriff
 */

@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {
    // Benutzer anhand der E-Mail finden (unique)
    Optional<Benutzer> findByEmail(String email);

    // Alle Benutzer mit einer bestimmten Rolle abrufen
    List<Benutzer> findByRolle(Rolle rolle);
}