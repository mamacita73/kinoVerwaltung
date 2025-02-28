package com.kino.repository;

import com.kino.entity.Sitz;
import kinoVerwaltung.Sitzstatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SitzRepository extends JpaRepository<Sitz, Long> {
    // Alle Sitze einer bestimmten Sitzreihe
    List<Sitz> findBySitzreiheId(Long sitzreiheId);

    //  Sitze nach Status (z.B. FREI, RESERVIERT)
    List<Sitz> findByStatus(Sitzstatus status);

    //  Alle Sitze einer Sitzreihe mit bestimmtem Status
    List<Sitz> findBySitzreiheIdAndStatus(Long sitzreiheId, Sitzstatus status);
}
