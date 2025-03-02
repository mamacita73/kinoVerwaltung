package com.kino.repository;

import com.kino.entity.Reservierung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Repository für Reservierung
@Repository
public interface ReservierungRepository extends JpaRepository<Reservierung, Long> {
    List<Reservierung> findByBenutzerId(Long benutzerId);
    List<Reservierung> findByBenutzerEmail(String email);
    List<Reservierung> findByStatus(String status);
    // Liefert eine einzelne Reservierung anhand der eindeutigen Reservierungsnummer
    Optional<Reservierung> findByReservierungsnummer(String reservierungsnummer);
}
