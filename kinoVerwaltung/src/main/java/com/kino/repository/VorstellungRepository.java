package com.kino.repository;


import com.kino.entity.Vorstellung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Repository für Vorstellung
// Repository für Vorstellung (relational, JPA)
@Repository
public interface VorstellungRepository extends JpaRepository<Vorstellung, Long> {
    List<Vorstellung> findByFilmTitel(String filmTitel);

    // Alle Vorstellungen in einem Saal
    List<Vorstellung> findBySaalId(Long saalId);

    // Diese Query-Methode lädt eine Vorstellung mitsamt dem zugehörigen Saal, seinen Sitzreihen und den Sitzen
    @Query("SELECT v FROM Vorstellung v " +
            "LEFT JOIN FETCH v.saal s " +
            "LEFT JOIN FETCH s.sitzreihen sr " +
            "LEFT JOIN FETCH sr.sitze " +
            "WHERE v.id = :id")
    Optional<Vorstellung> findByIdFetchSaalAndSitzreihen(@Param("id") Long id);;
}
