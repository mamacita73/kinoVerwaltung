package com.kino.repository;

import com.kino.mongo.VorstellungStats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VorstellungStatsRepository extends MongoRepository<VorstellungStats, Long> {
    // Optional: findByFilmTitel(...)
}