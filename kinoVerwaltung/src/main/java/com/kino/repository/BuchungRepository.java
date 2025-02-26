package com.kino.repository;

import com.kino.entity.Buchung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository für Buchung
@Repository
public interface BuchungRepository extends JpaRepository<Buchung, Long> {
    List<Buchung> findByBenutzerId(Long benutzerId);
    List<Buchung> findByBenutzerEmail(String email);
    List<Buchung> findByStatus(String status);
}