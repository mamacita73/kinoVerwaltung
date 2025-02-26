package com.kino.service;

import com.kino.entity.Saal;
import com.kino.repository.SaalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service für Saal
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
}