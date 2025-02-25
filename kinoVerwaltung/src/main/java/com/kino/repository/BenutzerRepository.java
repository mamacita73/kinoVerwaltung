package com.kino.repository;

import com.kino.entity.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Respository für Benutzer für DB-Zugriff
 */

@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {
}