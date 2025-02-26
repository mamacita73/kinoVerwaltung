package com.kino.service;

import com.kino.entity.Reservierung;
import com.kino.repository.ReservierungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Service für Reservierung
@Service
public class ReservierungService {
    @Autowired
    private ReservierungRepository reservierungRepository;

    public List<Reservierung> getReservierungenByBenutzer(Long benutzerId) {
        return reservierungRepository.findByBenutzerId(benutzerId);
    }

    public List<Reservierung> getReservierungenByStatus(String status) {
        return reservierungRepository.findByStatus(status);
    }

    public Reservierung saveReservierung(Reservierung reservierung) {
        return reservierungRepository.save(reservierung);
    }

    public void deleteReservierung(Long id) {
        reservierungRepository.deleteById(id);
    }
}