package com.kino.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository für Kino
@Repository
public interface KinoRepository extends JpaRepository<KinoRepository, Long> {}
