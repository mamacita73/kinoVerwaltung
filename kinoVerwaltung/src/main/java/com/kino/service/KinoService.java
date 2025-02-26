package com.kino.service;


import com.kino.entity.Kino;
import com.kino.entity.Saal;
import com.kino.repository.KinoRepository;
import com.kino.repository.SaalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KinoService {
    @Autowired
    private KinoRepository kinoRepository;

    @Autowired
    private SaalRepository saalRepository;

    public List<Kino> getAllKinos() {
        List<Kino> kinos = kinoRepository.findAll();
        System.out.println("Gefundene Kinos: " + kinos);
        return kinos;
    }

    public Optional<Kino> getKinoById(Long id) {
        return kinoRepository.findById(id);
    }

    public Kino saveKino(Kino kino) {
        return kinoRepository.save(kino);
    }

    public Kino updateKino(Long id, Kino kino) {
        if (kinoRepository.existsById(id)) {
            kino.setId(id);
            return kinoRepository.save(kino);
        }
        return null;
    }

    public void deleteKino(Long id) {
        kinoRepository.deleteById(id);
    }

    public List<Saal> getSaeleByKino(Long kinoId) {
        return saalRepository.findByKinoId(kinoId);
    }
}
