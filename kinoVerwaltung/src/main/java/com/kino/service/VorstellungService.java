package com.kino.service;

import com.kino.entity.Vorstellung;
import com.kino.repository.VorstellungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service für Vorstellung
@Service
public class VorstellungService {
    @Autowired
    private VorstellungRepository vorstellungRepository;

    public List<Vorstellung> getAllVorstellungen() {
        return vorstellungRepository.findAll();
    }

    public List<Vorstellung> getVorstellungByFilmTitel(String filmTitel) {
        return vorstellungRepository.findByFilmTitel(filmTitel);
    }
}
