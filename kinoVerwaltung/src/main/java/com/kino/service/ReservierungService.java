package com.kino.service;

import com.kino.entity.*;
import com.kino.repository.BenutzerRepository;
import com.kino.repository.ReservierungRepository;
import com.kino.repository.SitzRepository;
import com.kino.repository.VorstellungRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class ReservierungService {

    private final ReservierungRepository reservierungRepository;
    private final VorstellungRepository vorstellungRepository;
    private final SitzRepository sitzRepository;

    public ReservierungService(ReservierungRepository reservierungRepository,
                               VorstellungRepository vorstellungRepository,
                               SitzRepository sitzRepository) {
        this.reservierungRepository = reservierungRepository;
        this.vorstellungRepository = vorstellungRepository;
        this.sitzRepository = sitzRepository;
    }

    @Transactional
    public Reservierung reservierungAnlegen(Long vorstellungId,
                                            String kategorie,
                                            int anzahl,
                                            String kundenEmail,
                                            String datum,
                                            String status) {
        Vorstellung vorstellung = vorstellungRepository.findById(vorstellungId)
                .orElseThrow(() -> new IllegalArgumentException("Vorstellung mit ID " + vorstellungId + " nicht gefunden!"));

        // Prüfe, ob die Vorstellung bereits begonnen hat.
        LocalDate reservationDate = LocalDate.parse(datum);
        LocalTime startzeit = vorstellung.getStartzeit();
        LocalDateTime presentationStart = LocalDateTime.of(reservationDate, startzeit);
        if (LocalDateTime.now().isAfter(presentationStart)) {
            throw new IllegalStateException("Die Vorstellung hat bereits begonnen oder ist vorbei!");
        }

        List<Sitz> freieSitze = findeFreieSitze(vorstellung, kategorie, anzahl);
        if (freieSitze.size() < anzahl) {
            throw new IllegalStateException("Nicht genügend freie Plätze in " + kategorie);
        }

        Reservierung reservierung = new Reservierung();
        reservierung.setKundenEmail(kundenEmail);
        reservierung.setDatum(datum);
        reservierung.setStatus(status);
        reservierung.setVorstellung(vorstellung);
        reservierung.setReservierungsnummer(generateReservierungsnummer());

        List<ReservierungSitz> rsList = new ArrayList<>();
        for (Sitz sitz : freieSitze) {
            sitz.setStatus(Sitzstatus.RESERVIERT);
            ReservierungSitz rs = new ReservierungSitz();
            rs.setSitz(sitz);
            rs.setReservierung(reservierung);
            rsList.add(rs);
        }
        reservierung.setReservierungSitze(rsList);

        return reservierungRepository.save(reservierung);
    }

    private List<Sitz> findeFreieSitze(Vorstellung vorstellung, String kategorie, int anzahl) {
        if (!vorstellung.getSaal().isIstFreigegeben()) {
            throw new IllegalStateException("Saal ist nicht freigegeben!");
        }
        List<Sitz> freieSitze = new ArrayList<>();
        for (Sitzreihe sr : vorstellung.getSaal().getSitzreihen()) {
            freieSitze.addAll(sr.getSitze().stream()
                    .filter(sitz -> sitz.getStatus() == Sitzstatus.FREI &&
                            sitz.getKategorie().name().equalsIgnoreCase(kategorie))
                    .limit(anzahl - freieSitze.size())
                    .toList());
            if (freieSitze.size() >= anzahl) break;
        }
        return freieSitze;
    }

    @Transactional
    public String stornieren(Long reservierungId) {
        Reservierung reservierung = reservierungRepository.findById(reservierungId)
                .orElseThrow(() -> new IllegalArgumentException("Reservierung nicht gefunden!"));
        reservierung.getReservierungSitze().forEach(rs -> {
            Sitz sitz = rs.getSitz();
            if (sitz.getStatus() == Sitzstatus.RESERVIERT) {
                sitz.setStatus(Sitzstatus.FREI);
            }
        });
        reservierung.setStatus("STORNIERT");
        reservierungRepository.save(reservierung);
        return "Reservierung " + reservierung.getId() + " storniert.";
    }

    private String generateReservierungsnummer() {
        return String.valueOf(10000 + new Random().nextInt(90000));
    }

    public List<Reservierung> getReservierungenByEmail(String kundenEmail) {
        return reservierungRepository.findByKundenEmail(kundenEmail);
    }

    @Transactional(readOnly = true)
    public List<Reservierung> getReservierungenByEmailPerSitze(String kundenEmail) {
        return reservierungRepository.findAllWithSitzeAndVorstellungByEmail(kundenEmail);
    }
}
