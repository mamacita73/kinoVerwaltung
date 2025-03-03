package com.kino.controller;

import com.kino.mongo.VorstellungStats;
import com.kino.repository.VorstellungStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    private VorstellungStatsRepository statsRepo;

    // Bsp: GET /stats/vorstellung/2
    @GetMapping("/vorstellung/{id}")
    public ResponseEntity<VorstellungStats> getVorstellungStats(@PathVariable("id") Long id) {
        return statsRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Bsp: GET /stats/film/Avatar
    @GetMapping("/film/{filmTitel}")
    public List<VorstellungStats> getStatsByFilm(@PathVariable("filmTitel") String filmTitel) {
        // Filtern z. B. clientseitig oder per Custom Query
        return statsRepo.findAll()
                .stream()
                .filter(s -> filmTitel.equalsIgnoreCase(s.getFilmTitel()))
                .collect(Collectors.toList());
    }
}