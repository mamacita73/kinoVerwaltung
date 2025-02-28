package com.kino.service;

import com.kino.entity.Saal;
import com.kino.entity.Sitz;
import com.kino.entity.Sitzreihe;
import com.kino.repository.SaalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaalService {

    @Autowired
    private SaalRepository saalRepository;

    public List<Saal> getAllSäle() {
        return saalRepository.findAll();
    }

    public Optional<Saal> getSaalById(Long id) {
        return saalRepository.findById(id);
    }

    public List<Saal> getSäleByKinoId(Long kinoId) {
        return saalRepository.findByKinoId(kinoId);
    }

    public Saal saveSaal(Saal saal) {
        return saalRepository.save(saal);
    }

    public void deleteSaal(Long id) {
        saalRepository.deleteById(id);
    }

    //  Anlegen eines neuen Saals inkl. Sitzreihen und Sitzen
    public Saal anlegenSaal(Saal saal) {
        // Prüfen, ob Sitzreihen existieren
        if (saal.getSitzreihen() != null) {
            for (Sitzreihe reihe : saal.getSitzreihen()) {
                // Jede Reihe dem Saal zuordnen
                reihe.setSaal(saal);

                // Prüfen, ob Sitze vorhanden sind
                if (reihe.getSitze() != null) {
                    for (Sitz sitz : reihe.getSitze()) {
                        // Sitzreihe in jedem Sitz setzen
                        sitz.setSitzreihe(reihe);
                    }
                }
            }
        }

        // Jetzt speichern
        return saalRepository.save(saal);
    }

}
