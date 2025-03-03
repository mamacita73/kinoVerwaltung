package com.kino.repository;

import com.kino.entity.Vorstellung;
import com.kino.mongo.VorstellungStats;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repository für VorstellungStats (MongoDB)
@Repository
public interface VorstellungStatsRepository extends MongoRepository<VorstellungStats, Long> {
    // Optional können Sie weitere Mongo-spezifische Methoden hinzufügen, z.B.:
    Optional<VorstellungStats> findByFilmTitel(String filmTitel);
}