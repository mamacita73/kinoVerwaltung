package com.kino.repository;

import com.kino.entity.Saal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Repository für Saal
@Repository
public interface SaalRepository extends JpaRepository<Saal, Long> {
    List<Saal> findByKinoId(Long kinoId); // Säle in einem Kino
}
