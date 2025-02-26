package com.kino.service;

import com.kino.entity.Benutzer;
import com.kino.repository.BenutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * für die Kommunikation mit der DB
 */


@Service
public class BenutzerService {
    @Autowired
    private BenutzerRepository benutzerRepository;


    public Optional<Benutzer> getBenutzerByEmail(String email) {
        return benutzerRepository.findByEmail(email);
    }

    public Benutzer saveBenutzer(Benutzer benutzer){
        return benutzerRepository.save(benutzer);
    }
}