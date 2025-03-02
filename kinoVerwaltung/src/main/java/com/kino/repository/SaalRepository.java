package com.kino.repository;

import com.kino.entity.Saal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Repository für Saal
@Repository
public interface SaalRepository extends JpaRepository<Saal, Long> {
    List<Saal> findByKinoId(Long kinoId); // Säle in einem Kino
    Optional<Saal> findByName(String name);

    // Custom-Query mit JOIN FETCH, um Vorstellungen in einem Rutsch zu laden
    @Query("SELECT DISTINCT s FROM Saal s LEFT JOIN FETCH s.vorstellungen")
    List<Saal> findAllSaeleMitVorstellungen();
}
