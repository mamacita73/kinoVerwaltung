package com.kino.command;


import com.kino.dto.MultiVorstellungenDTO;
import com.kino.dto.SaeleMitVorstellungenDTO;
import com.kino.dto.VorstellungDTO;
import com.kino.entity.*;
import com.kino.repository.*;
import com.kino.service.ReservierungService;
import com.kino.service.SaalService;
import com.kino.service.VorstellungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CommandFactory {
    private final BenutzerRepository benutzerRepository;
    private final VorstellungRepository vorstellungRepository;
    private final  VorstellungService vorstellungService;
    private final BuchungRepository buchungRepository;
    private final KinoRepository kinoRepository;
    private final ReservierungRepository reservierungRepository;
    private final ReservierungService reservierungService;
    private final SaalRepository saalRepository;
    private final SaalService saalService;

    @Autowired
    public CommandFactory(BenutzerRepository benutzerRepository,
                          VorstellungRepository vorstellungRepository, VorstellungService vorstellungService,
                          BuchungRepository buchungRepository,
                          KinoRepository kinoRepository,
                          ReservierungRepository reservierungRepository, ReservierungService reservierungService,
                          SaalRepository saalRepository, SaalService saalService) {
        this.benutzerRepository = benutzerRepository;
        this.vorstellungRepository = vorstellungRepository;
        this.vorstellungService = vorstellungService;
        this.buchungRepository = buchungRepository;
        this.kinoRepository = kinoRepository;
        this.reservierungRepository = reservierungRepository;
        this.reservierungService = reservierungService;
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
                return new GenericCommand<Reservierung>(() -> {
                    System.out.println("=== [CommandFactory] Erstelle RESERVIERUNG_WRITE-Command ===");

                    Map<String, Object> innerPayload = (Map<String, Object>) payload.get("payload");
                    if (innerPayload == null) {
                        innerPayload = payload;
                    }

                    Long vorstellungId = ((Number) innerPayload.get("vorstellungId")).longValue();
                    String kategorie = (String) innerPayload.get("kategorie");
                    int anzahl = ((Number) innerPayload.get("anzahl")).intValue();
                    String kundenEmail = (String) innerPayload.get("kundenEmail");
                    String datum = (String) innerPayload.getOrDefault("datum", "2025-03-02");
                    String status = (String) innerPayload.getOrDefault("status", "RESERVIERT");

                    // Aufruf des @Transactional-Services
                    Reservierung savedRes = reservierungService.reservierungAnlegen(
                            vorstellungId,
                            kategorie,
                            anzahl,
                            kundenEmail,
                            datum,
                            status
                    );

                    System.out.println("=== [CommandFactory] Reservierung gespeichert, ID: " + savedRes.getId() + " ===");
                    return savedRes;
                });

            case "RESERVIERUNG_QUERY_BY_EMAIL":
                return new GenericCommand<List<Reservierung>>(() -> {
                    System.out.println("=== [CommandFactory] RESERVIERUNG_QUERY_BY_EMAIL ===");
                    String email = (String) payload.get("kundenEmail");
                    if (email == null || email.isEmpty()) {
                        throw new RuntimeException("kundenEmail fehlt!");
                    }
                    // Rufe Service-Methode auf
                    return reservierungService.getReservierungenByEmail(email);
                });

            case "RESERVIERUNG_BUCHEN":
                return new GenericCommand<Buchung>(() -> {
                    System.out.println("=== [CommandFactory] RESERVIERUNG_BUCHEN ===");
                    String reservierungsNummer = (String) payload.get("reservierungsnummer");
                    String zahlweise = (String) payload.getOrDefault("zahlweise", "PAYPAL");
                    // ... Logik: reservierung → buchung
                    return reservierungService.reservierungZuBuchung(reservierungsNummer, zahlweise);
                });

            case "RESERVIERUNG_CANCEL":
                return new GenericCommand<String>(() -> {
                    Long reservierungId = ((Number) payload.get("reservierungId")).longValue();
                    Reservierung r = reservierungRepository.findById(reservierungId)
                            .orElseThrow(() -> new RuntimeException("Reservierung nicht gefunden"));

                    // 1) Sitze freigeben, die in dieser Reservierung "RESERVIERT" wurden
                    //   -> Hier müsstest du in einer realen App Sitzbelegungen pro Vorstellung führen
                    //   oder den Sitzstatus + Vorstellungsbezug tracken.
                    //   Da das Beispiel kein Sitz->Vorstellung hat,
                    //   müsstest du ggf. "RESERVIERUNG" die Sitze mappen. (Fehlt im Modell.)

                    // 2) Reservierung auf "STORNIERT" setzen oder löschen
                    r.setStatus("STORNIERT");
                    reservierungRepository.save(r);

                    return "Reservierung " + r.getId() + " wurde storniert.";
                });


            case "SAAL_WRITE":
                return new GenericCommand<Saal>(() -> {
                    System.out.println("=== [CommandFactory] Erstelle SAAL_WRITE-Command ===");

                    // Extrahiere den inneren Payload, da die Daten doppelt verschachtelt sind
                    Map<String, Object> innerPayload = payload;
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

            case "VORSTELLUNG_MULTI_WRITE":
            return new GenericCommand<List<Vorstellung>>(() -> {
                System.out.println("=== [CommandFactory] Erstelle VORSTELLUNG_MULTI_WRITE-Command ===");
                /// 1) Payload extrahieren
                Map<String, Object> innerPayload = (Map<String, Object>) payload.get("payload");
                if (innerPayload == null) {
                    innerPayload = payload;
                }

                // 2) Felder auslesen
                String filmTitel = (String) innerPayload.get("filmTitel");
                List<Long> saalIds = ((List<?>) innerPayload.get("saalIds"))
                        .stream().map(obj -> ((Number) obj).longValue())
                        .collect(Collectors.toList());

                List<String> startzeiten = ((List<?>) innerPayload.get("startzeiten"))
                        .stream().map(Object::toString)
                        .collect(Collectors.toList());


                int dauerMinuten = ((Number) innerPayload.get("dauerMinuten")).intValue();

                // 3) MultiVorstellungDTO aufbauen
                MultiVorstellungenDTO dto = new MultiVorstellungenDTO(
                        filmTitel,
                        saalIds,
                        startzeiten,
                        dauerMinuten
                );

                // 4) Service aufrufen
                List<Vorstellung> created = vorstellungService.anlegenMehrereVorstellungen(dto);

                System.out.println("=== [CommandFactory] " + created.size() + " Vorstellungen erstellt ===");
                return created;
            });

            case "VORSTELLUNG_QUERY_ALL":
                return new GenericCommand<List<Vorstellung>>(() -> {
                    System.out.println("=== [CommandFactory] Erstelle VORSTELLUNG_QUERY_ALL-Command ===");
                    List<Vorstellung> list = vorstellungRepository.findAll();
                    return list;
                });


            case "VORSTELLUNG_VERFUEGBAR":
                return new GenericCommand<Map<String, Integer>>(() -> {
                    System.out.println("=== [CommandFactory] Erstelle VORSTELLUNG_VERFUEGBAR-Command ===");
                    // Hole die Vorstellung anhand der ID aus dem Payload
                    Long id = ((Number) payload.get("id")).longValue();
                    Vorstellung vorstellung = vorstellungRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Vorstellung nicht gefunden, ID=" + id));
                    // Hole den Saal und berechne die Verfügbarkeit
                    Saal saal = vorstellung.getSaal();
                    if (saal == null) {
                        throw new RuntimeException("Vorstellung hat keinen Saal!");
                    }
                    Map<String, Integer> availability = new HashMap<>();
                    availability.put("PARKETT", 0);
                    availability.put("LOGE", 0);
                    availability.put("LOGE_SERVICE", 0);
                    for (Sitzreihe sr : saal.getSitzreihen()) {
                        for (Sitz sitz : sr.getSitze()) {
                            if (sitz.getStatus().equals(Sitzstatus.FREI)) {
                                String catName = sitz.getKategorie().name();
                                availability.put(catName, availability.get(catName) + 1);
                            }
                        }
                    }
                    return availability;
                });


            case "SAELE_MIT_VORSTELLUNGEN_QUERY":
                return new GenericCommand<List<SaeleMitVorstellungenDTO>>(() -> {
                    List<Saal> saele = saalService.getAllSaeleMitVorstellungen();
                    List<SaeleMitVorstellungenDTO> dtos = saele.stream().map(saal -> {
                        List<VorstellungDTO> vDtos = saal.getVorstellungen().stream()
                                .map(v -> new VorstellungDTO(
                                        v.getId(),
                                        saal.getId(),
                                        v.getFilmTitel(),
                                        v.getStartzeit().toString(),
                                        v.getDauerMinuten()
                                ))
                                .collect(Collectors.toList());
                        return new SaeleMitVorstellungenDTO(
                                saal.getId(),
                                saal.getName(),
                                saal.getAnzahlReihen(),
                                saal.isIstFreigegeben(),
                                vDtos
                        );
                    }).collect(Collectors.toList());
                    return dtos;
                });

            default:
                throw new IllegalArgumentException("Unbekannter Command-Typ: " + commandType);
        }
    }
}
