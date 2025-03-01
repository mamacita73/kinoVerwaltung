package com.kino.command;


import com.kino.entity.*;
import com.kino.repository.*;
import com.kino.service.SaalService;
import com.kino.service.VorstellungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    private final SaalService saalService;

    @Autowired
    public CommandFactory(BenutzerRepository benutzerRepository,
                          VorstellungRepository vorstellungRepository,
                          BuchungRepository buchungRepository,
                          KinoRepository kinoRepository,
                          ReservierungRepository reservierungRepository,
                          SaalRepository saalRepository, SaalService saalService) {
        this.benutzerRepository = benutzerRepository;
        this.vorstellungRepository = vorstellungRepository;
        this.buchungRepository = buchungRepository;
        this.kinoRepository = kinoRepository;
        this.reservierungRepository = reservierungRepository;
        this.saalRepository = saalRepository;
        this.saalService = saalService;
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
                return new GenericCommand<Saal>(() -> {
                    System.out.println("=== [CommandFactory] Erstelle SAAL_WRITE-Command ===");

                    // Extrahiere den inneren Payload, da die Daten doppelt verschachtelt sind
                    Map<String, Object> innerPayload = (Map<String, Object>) payload.get("payload");
                    System.out.println("=== [CommandFactory] Inner Payload: " + innerPayload);

                    // Erstelle das Saal-Objekt
                    Saal saal = new Saal();
                    saal.setName((String) innerPayload.get("name"));
                    saal.setAnzahlReihen(((Number) innerPayload.get("anzahlReihen")).intValue());
                    saal.setIstFreigegeben((boolean) innerPayload.get("istFreigegeben"));

                    // Setze ein Standard-Kino (hier aus der DB, ID=1)
                    Kino defaultKino = kinoRepository.findById(1L)
                            .orElseThrow(() -> new RuntimeException("Default-Kino (ID=1) nicht gefunden!"));
                    saal.setKino(defaultKino);

                    // Sitzreihen verarbeiten
                    List<Map<String, Object>> reihenPayload = (List<Map<String, Object>>) innerPayload.get("sitzreihen");
                    if (reihenPayload != null) {
                        List<Sitzreihe> sitzreihen = new ArrayList<>();
                        for (Map<String, Object> reiheMap : reihenPayload) {
                            Sitzreihe reihe = new Sitzreihe();
                            reihe.setReihenBezeichnung((String) reiheMap.get("reihenBezeichnung"));
                            // Achte auf den korrekten Schlüssel; hier nehmen wir "anzahlSitze"
                            reihe.setAnzahlSitze(((Number) reiheMap.get("anzahlSitze")).intValue());
                            reihe.setSaal(saal);
                            System.out.println("=== [CommandFactory] Verarbeite Sitzreihe: " + reihe.getReihenBezeichnung() + " mit " + reihe.getAnzahlSitze() + " Sitzen ===");

                            // Sitze pro Reihe verarbeiten
                            List<Map<String, Object>> sitzePayload = (List<Map<String, Object>>) reiheMap.get("sitze");
                            if (sitzePayload != null) {
                                List<Sitz> sitze = new ArrayList<>();
                                for (Map<String, Object> sitzMap : sitzePayload) {
                                    Sitz sitz = new Sitz();
                                    sitz.setNummer(((Number) sitzMap.get("nummer")).intValue());
                                    sitz.setKategorie(Sitzkategorie.valueOf((String) sitzMap.get("kategorie")));
                                    sitz.setStatus(Sitzstatus.valueOf((String) sitzMap.get("status")));
                                    sitz.setSitzreihe(reihe);
                                    sitze.add(sitz);
                                    System.out.println("=== [CommandFactory] Verarbeite Sitz Nr. " + sitz.getNummer() + " in Reihe " + reihe.getReihenBezeichnung() + " ===");
                                }
                                reihe.setSitze(sitze);
                            }
                            sitzreihen.add(reihe);
                        }
                        saal.setSitzreihen(sitzreihen);
                    }

                    // Persistierung über den Service
                    System.out.println("=== [CommandFactory] Persistiere Saal über Service ===");
                    Saal saved = saalService.anlegenSaal(saal);
                    System.out.println("=== [CommandFactory] Saal gespeichert, ID: " + saved.getId() + " ===");
                    return saved;
                });

            case "VORSTELLUNG_WRITE":
                return new GenericCommand<Vorstellung>(() -> {
                    System.out.println("=== [CommandFactory] Erstelle VORSTELLUNG_WRITE-Command ===");
                    // Extrahiere den inneren Payload (falls doppelt verschachtelt; andernfalls direkt)
                    Map<String, Object> innerPayload = (Map<String, Object>) payload.get("payload");
                    if (innerPayload == null) {
                        innerPayload = payload;
                    }
                    // Werte aus dem Payload auslesen
                    Long saalId = ((Number) innerPayload.get("saalId")).longValue();
                    String filmTitel = (String) innerPayload.get("filmTitel");
                    String startzeitStr = (String) innerPayload.get("startzeit");
                    int dauerMinuten = ((Number) innerPayload.get("dauerMinuten")).intValue();

                    // Saal laden
                    Saal saal = saalRepository.findById(saalId)
                            .orElseThrow(() -> new RuntimeException("Saal mit ID=" + saalId + " nicht gefunden!"));

                    // Vorstellung-Objekt erstellen und befüllen
                    Vorstellung vorstellung = new Vorstellung();
                    vorstellung.setFilmTitel(filmTitel);
                    // Konvertiere den String in LocalTime (z.B. "16:00")
                    vorstellung.setStartzeit(java.time.LocalTime.parse(startzeitStr));
                    vorstellung.setDauerMinuten(dauerMinuten);
                    vorstellung.setSaal(saal);

                    // Persistieren
                    Vorstellung saved = vorstellungRepository.save(vorstellung);
                    System.out.println("=== [CommandFactory] Vorstellung gespeichert, ID: " + saved.getId() + " ===");
                    return saved;
                });




            default:
                throw new IllegalArgumentException("Unbekannter Command-Typ: " + commandType);
        }
    }
}
