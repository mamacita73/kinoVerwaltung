package com.kino.repository;

import com.kino.entity.Vorstellung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository für Vorstellung
@Repository
public interface VorstellungRepository extends JpaRepository<Vorstellung, Long> {
    List<Vorstellung> findByFilmTitel(String filmTitel);

    // Alle Vorstellungen in einem Saal
    List<Vorstellung> findBySaalId(Long saalId);
}