package com.kino.command;


import com.kino.entity.*;
import com.kino.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class CommandFactory {
    private final BenutzerRepository benutzerRepository;
    private final VorstellungRepository vorstellungRepository;
    private final BuchungRepository buchungRepository;
    private final KinoRepository kinoRepository;
    private final ReservierungRepository reservierungRepository;
    private final SaalRepository saalRepository;

    @Autowired
    public CommandFactory(BenutzerRepository benutzerRepository,
                          VorstellungRepository vorstellungRepository,
                          BuchungRepository buchungRepository,
                          KinoRepository kinoRepository,
                          ReservierungRepository reservierungRepository,
                          SaalRepository saalRepository) {
        this.benutzerRepository = benutzerRepository;
        this.vorstellungRepository = vorstellungRepository;
        this.buchungRepository = buchungRepository;
        this.kinoRepository = kinoRepository;
        this.reservierungRepository = reservierungRepository;
        this.saalRepository = saalRepository;
    }


    /**
     * Erzeugt anhand des commandType und des payload's den passenden Command.
     */
    public Command<?> createCommand(String commandType, Map<String, Object> payload) {
        switch (commandType) {
            case "BENUTZER_WRITE":
                // Schreibt die Daten eines Benutzers in die Datenbank
                return new GenericCommand<Benutzer>(() -> {
                    Benutzer benutzer = new Benutzer();

                    benutzer.setBenutzername((String) payload.get("benutzername"));
                    benutzer.setEmail((String) payload.get("email"));
                    benutzer.setPasswort((String) payload.get("passwort"));
                    benutzer.setId((Long) payload.get("id"));
                    String rolleString = (String) payload.get("rolle");
                    if (rolleString != null) {
                        benutzer.setRolle(Rolle.valueOf(rolleString.toUpperCase()));
                    } else {
                        // Optional: Standardwert setzen oder Fehler werfen
                        benutzer.setRolle(Rolle.KUNDE);
                    }
                    benutzer.setBuchungen((List<Buchung>) payload.get("buchungen"));

                    return benutzerRepository.save(benutzer);
                });
            case "VORSTELLUNG_WRITE":
                // Erstellt und speichert eine neue Vorstellung
                return new GenericCommand<Vorstellung>(() -> {
                    Vorstellung vorstellung = new Vorstellung();

                    vorstellung.setFilmTitel((String) payload.get("filmName"));
                    vorstellung.setDauerMinuten((int) payload.get("dauer"));
                    vorstellung.setId((Long) payload.get("id"));
                    vorstellung.setSaal((Saal) payload.get("saal"));
                    vorstellung.setStartzeit((String) payload.get("startzeit"));
                    vorstellung.setBuchungen((List<Buchung>) payload.get("buchungen"));

                    return vorstellungRepository.save(vorstellung);
                });
            case "BUCHUNG_QUERY":
                // Suche eine Buchung anhand einer BuchungsNummer
                return new GenericCommand<Optional<Buchung>>(() -> {
                    Long buchungsNummer = Long.valueOf(payload.get("buchungsNummer").toString());

                    return buchungRepository.findById(buchungsNummer);
                });
            case "KINO_QUERY":
                //  Suche ein Kino anhand des Namens
                return new GenericCommand<Optional<Kino>>(() -> {
                    //TODO: Implementierung fehlt
                    return Optional.empty();
                });
            case "RESERVIERUNG_WRITE":
                // Erstelle eine neue Reservierung
                return new GenericCommand<Reservierung>(() -> {
                    Reservierung reservierung = new Reservierung();

                    reservierung.setBenutzer((Benutzer) payload.get("kundenName"));
                    reservierung.setVorstellung((Vorstellung) payload.get("vorstellung"));
                    reservierung.setId((Long) payload.get("id"));
                    reservierung.setReservierungsnummer((String) payload.get("reservierungsNummer"));
                    reservierung.setStatus((String) payload.get("status"));
                    reservierung.setDatum((String) payload.get("datum"));

                    return reservierungRepository.save(reservierung);
                });
            case "SAAL_WRITE":
                // Erstelle einen neuen Saal
                return new GenericCommand<Saal>(() -> {
                    Saal saal = new Saal();

                    saal.setId((Long) payload.get("id"));
                    saal.setAnzahlReihen((int) payload.get("anzahlReihen"));
                    saal.setName((String) payload.get("name"));
                    saal.setKino((Kino) payload.get("kino"));
                    saal.setVorstellungen((List<Vorstellung>) payload.get("vorstellungen"));
                    saal.setIstFreigegeben((boolean) payload.get("istFreigegeben"));

                    return saalRepository.save(saal);
                });
            default:
                throw new IllegalArgumentException("Unbekannter Command-Typ: " + commandType);
        }
    }
}
