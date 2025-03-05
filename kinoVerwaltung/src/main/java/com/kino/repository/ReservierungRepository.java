package com.kino.repository;

import com.kino.entity.Reservierung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Repository für Reservierung
@Repository
public interface ReservierungRepository extends JpaRepository<Reservierung, Long> {
    List<Reservierung> findByBenutzerId(Long benutzerId);
    List<Reservierung> findByKundenEmail(String email);
    List<Reservierung> findByStatus(String status);
    // Liefert eine einzelne Reservierung anhand der eindeutigen Reservierungsnummer
    Optional<Reservierung> findByReservierungsnummer(String reservierungsnummer);
    @Query("SELECT DISTINCT r FROM Reservierung r " +
            "LEFT JOIN FETCH r.reservierungSitze rs " +
            "LEFT JOIN FETCH r.vorstellung " +
            "WHERE r.kundenEmail = :email")
    List<Reservierung> findAllWithSitzeAndVorstellungByEmail(@Param("email") String email);

}
