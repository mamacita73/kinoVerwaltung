package com.kino.repository;

import kinoVerwaltung.Sitzreihe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SitzreiheRepository extends JpaRepository<Sitzreihe, Long> {
    // Alle Sitzreihen in einem Saal
    List<Sitzreihe> findBySaalId(Long saalId);
    // Sitzreihe im Saal nach Bezeichnung (z.B. Reihe "A")
    Optional<Sitzreihe> findBySaalIdAndReihenBezeichnung(Long saalId, String reihenBezeichnung);
}
