package com.kino.service;

import com.kino.entity.Benutzer;
import com.kino.repository.BenutzerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * für die KOmmunikation mit der DB
 */


@Service
public class BenutzerService {
    private final BenutzerRepository benutzerRepository;

    public BenutzerService(BenutzerRepository benutzerRepository) {
        this.benutzerRepository = benutzerRepository;
    }

    public List<Benutzer> getAlleBenutzer() {
        return benutzerRepository.findAll();
    }
}