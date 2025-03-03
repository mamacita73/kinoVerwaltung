package com.kino.service;

import com.kino.entity.*;
import com.kino.repository.ReservierungRepository;
import com.kino.repository.SitzRepository; // Angenommen
import com.kino.repository.VorstellungRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        // 1) Vorstellung laden
        Vorstellung vorstellung = vorstellungRepository.findById(vorstellungId)
                .orElseThrow(() -> new RuntimeException("Vorstellung nicht gefunden!"));

        // 2) Freie Sitze in der gewünschten Kategorie suchen
        List<Sitz> freieSitze = findFreieSitzeInVorstellung(vorstellung, kategorie, anzahl);
        if (freieSitze.size() < anzahl) {
            throw new RuntimeException("Nicht genügend freie Plätze in " + kategorie);
        }

        // 3) Reservierung anlegen
        Reservierung reservierung = new Reservierung();
        reservierung.setKundenEmail(kundenEmail);
        reservierung.setDatum(datum);
        reservierung.setStatus(status); // "RESERVIERT"
        reservierung.setVorstellung(vorstellung);
        reservierung.setReservierungsnummer(generateReservierungsnummer());

        // 4) Sitzstatus auf RESERVIERT setzen + ReservierungSitz anlegen
        List<ReservierungSitz> rsList = new ArrayList<>();
        for (int i = 0; i < anzahl; i++) {
            Sitz sitz = freieSitze.get(i);
            sitz.setStatus(Sitzstatus.RESERVIERT);
            // sitzRepository.save(sitz); // wird durch Cascade evtl. übernommen

            ReservierungSitz rs = new ReservierungSitz();
            rs.setSitz(sitz);
            rs.setReservierung(reservierung);
            rsList.add(rs);
        }
        reservierung.setReservierungSitze(rsList);

        return reservierungRepository.save(reservierung);
    }

    // Hilfsmethode: Sucht SITZE (Saal -> Sitzreihen -> Sitze), die FREI und in der gewünschten Kategorie sind
    private List<Sitz> findFreieSitzeInVorstellung(Vorstellung vorstellung, String kategorie, int anzahl) {
        List<Sitz> result = new ArrayList<>();
        Saal saal = vorstellung.getSaal();
        if (!saal.isIstFreigegeben()) {
            throw new RuntimeException("Saal ist nicht freigegeben!");
        }
        for (Sitzreihe sr : saal.getSitzreihen()) {
            for (Sitz sitz : sr.getSitze()) {
                if (sitz.getStatus() == Sitzstatus.FREI &&
                        sitz.getKategorie().name().equalsIgnoreCase(kategorie)) {
                    result.add(sitz);
                }
                if (result.size() == anzahl) break;
            }
            if (result.size() == anzahl) break;
        }
        return result;
    }

    @Transactional
    public String stornieren(Long reservierungId) {
        Reservierung res = reservierungRepository.findById(reservierungId)
                .orElseThrow(() -> new RuntimeException("Reservierung nicht gefunden!"));

        // 1) Sitze wieder FREI
        for (ReservierungSitz rs : res.getReservierungSitze()) {
            Sitz sitz = rs.getSitz();
            if (sitz.getStatus() == Sitzstatus.RESERVIERT) {
                sitz.setStatus(Sitzstatus.FREI);
            }
        }
        // 2) Status auf STORNIERT
        res.setStatus("STORNIERT");
        reservierungRepository.save(res);
        return "Reservierung " + res.getId() + " storniert.";
    }

    private String generateReservierungsnummer() {
        int randomNum = 10000 + new Random().nextInt(90000);
        return String.valueOf(randomNum);
    }
}
