package com.kino.repository;

import com.kino.entity.Kino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repository für Kino
@Repository
public interface KinoRepository extends JpaRepository<Kino, Long> {
    Optional<Kino> findByName(String name);
    Optional<Kino> findByAdresse(String adresse);


}
