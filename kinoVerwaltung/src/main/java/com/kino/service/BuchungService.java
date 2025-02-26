package com.kino.service;

import com.kino.entity.Buchung;
import com.kino.repository.BuchungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service für Buchung
@Service
public class BuchungService {
    @Autowired
    private BuchungRepository buchungRepository;

    public List<Buchung> getBuchungenByBenutzer(Long benutzerId) {
        return buchungRepository.findByBenutzerId(benutzerId);
    }

    public Buchung saveBuchung(Buchung buchung) {
        return buchungRepository.save(buchung);
    }
}