package com.kino.command;


import com.kino.dto.*;
import com.kino.entity.*;
import com.kino.repository.*;
import com.kino.service.BuchungService;
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
    private final BuchungService buchungService;

    @Autowired
    public CommandFactory(BenutzerRepository benutzerRepository,
                          VorstellungRepository vorstellungRepository, VorstellungService vorstellungService,
                          BuchungRepository buchungRepository,
                          KinoRepository kinoRepository,
                          ReservierungRepository reservierungRepository, ReservierungService reservierungService,
                          SaalRepository saalRepository, SaalService saalService, BuchungService buchungService) {
        this.benutzerRepository = benutzerRepository;
        this.vorstellungRepository = vorstellungRepository;
        this.vorstellungService = vorstellungService;
        this.buchungRepository = buchungRepository;
        this.kinoRepository = kinoRepository;
        this.reservierungRepository = reservierungRepository;
        this.reservierungService = reservierungService;
        this.saalRepository = saalRepository;
        this.saalService = saalService;
        this.buchungService = buchungService;
    }


    /**
     * Erzeugt anhand des commandType und des payload's den passenden Command.
     */
    public Command<?> createCommand(String commandType, Map<String, Object> payload) {
        switch (commandType) {
            case "BENUTZER_WRITE":
                return new GenericCommand<Benutzer>(() -> {
                    Benutzer benutzer = new Benutzer();
                    benutzer.setBenutzername((String) payload.get("benutzername"));
                    benutzer.setEmail((String) payload.get("email"));
                    benutzer.setPasswort((String) payload.get("passwort"));
                    String rolleString = (String) payload.get("rolle");
                    if (rolleString != null) {
                        benutzer.setRolle(Rolle.valueOf(rolleString.toUpperCase()));
                    } else {
                        benutzer.setRolle(Rolle.KUNDE);
                    }
                    return benutzerRepository.save(benutzer);
                });


            case "LOGIN":
                return new GenericCommand<Map<String, Object>>(() -> {
                    //  Suche den Benutzer anhand der E-Mail
                    String email = (String) payload.get("email");
                    Optional<Benutzer> benutzerOpt = benutzerRepository.findByEmail(email);
                    if (benutzerOpt.isPresent()) {
                        Benutzer benutzer = benutzerOpt.get();
                        Map<String, Object> result = new HashMap<>();
                        result.put("success", "true");
                        result.put("email", benutzer.getEmail());
                        result.put("rolle", benutzer.getRolle().toString());
                        return result;
                    } else {
                        Map<String, Object> result = new HashMap<>();
                        result.put("success", "false");
                        result.put("message", "Benutzer nicht gefunden.");
                        return result;
                    }
                });


            case "RESERVIERUNG_WRITE":
                return new GenericCommand<Reservierung>(() -> {
                    System.out.println("=== [CommandFactory] Erstelle RESERVIERUNG_WRITE-Command ===");
                    Long vorstellungId = ((Number) payload.get("vorstellungId")).longValue();
                    String kategorie = (String) payload.get("kategorie");
                    int anzahl = ((Number) payload.get("anzahl")).intValue();
                    String kundenEmail = (String) payload.get("kundenEmail");
                    String datum = (String) payload.getOrDefault("datum", "2025-03-02");
                    String status = (String) payload.getOrDefault("status", "RESERVIERT");

                    return reservierungService.reservierungAnlegen(
                            vorstellungId, kategorie, anzahl, kundenEmail, datum, status
                    );
                });

            case "BUCHUNG_WRITE":
                return new GenericCommand<BuchungDTO>(() -> {
                    Long vorstellungId = ((Number) payload.get("vorstellungId")).longValue();
                    String kategorie = (String) payload.get("kategorie");
                    int anzahl = ((Number) payload.get("anzahl")).intValue();
                    String kundenEmail = (String) payload.get("kundenEmail");
                    // Die Buchung wird angelegt und als Objekt zurückgegeben

                    // Buchung anlegen
                    Buchung buchung = buchungService.buchungAnlegen(vorstellungId, kategorie, anzahl, kundenEmail);

                    // Nur die benötigten Felder in ein DTO packen
                    BuchungDTO dto = new BuchungDTO(
                            buchung.getBuchungsnummer(),
                            buchung.getStatus(),
                            buchung.getSumme()
                    );
                    return dto;
                });

            case "RESERVIERUNG_BUCHEN":
                return new GenericCommand<BuchungDTO>(() -> {
                    Long reservierungId = ((Number) payload.get("reservierungId")).longValue();
                    Buchung buchung = buchungService.reservierungZuBuchung(reservierungId);

                    BuchungDTO dto = new BuchungDTO();
                    dto.setBuchungsnummer(buchung.getBuchungsnummer());
                    dto.setStatus(buchung.getStatus());
                    dto.setSumme(buchung.getSumme());

                    return dto;
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


            case "RESERVIERUNG_QUERY_BY_EMAIL":
                return new GenericCommand<List<ReservierungDTO>>(() -> {
                    System.out.println("=== [CommandFactory] RESERVIERUNG_QUERY_BY_EMAIL ===");

                    String emailVal = (String) payload.get("kundenEmail");
                    if (emailVal == null || emailVal.isEmpty()) {
                        throw new IllegalArgumentException("kundenEmail fehlt!");
                    }

                    // 1) Reservierungen laden
                    List<Reservierung> reservierungen = reservierungService.getReservierungenByEmailPerSitze(emailVal);

                    // 2) Entities -> DTOs
                    List<ReservierungDTO> dtoList = new java.util.ArrayList<>();
                    for (Reservierung r : reservierungen) {
                        ReservierungDTO dto = new ReservierungDTO();
                        dto.setId(r.getId());
                        dto.setReservierungsnummer(r.getReservierungsnummer());
                        dto.setDatum(r.getDatum());
                        dto.setStatus(r.getStatus());
                        dto.setKundenEmail(r.getKundenEmail());

                        // Vorstellung (Film)
                        if (r.getVorstellung() != null) {
                            dto.setVorstellungId(r.getVorstellung().getId());
                            dto.setFilmTitel(r.getVorstellung().getFilmTitel());
                            if (r.getVorstellung().getStartzeit() != null) {
                                dto.setStartzeit(r.getVorstellung().getStartzeit().toString());
                            }
                        }

                        // 3) Kategorie, Anzahl und Summe aus den Sitz-Objekten ermitteln
                        List<ReservierungSitz> sitzListe = r.getReservierungSitze();
                        dto.setAnzahl(sitzListe.size()); // Anzahl Sitz-Einträge = Anzahl reservierter Plätze

                        // Falls alle Sitze dieselbe Kategorie haben, nimm einfach die erste
                        String cat = null;
                        int summe = 0;
                        for (ReservierungSitz rs : sitzListe) {
                            Sitz s = rs.getSitz();
                            // Setze die Kategorie nur einmal (falls du nur EINE pro Reservierung hast)
                            if (cat == null && s != null && s.getKategorie() != null) {
                                cat = s.getKategorie().name();
                            }

                            // Preise berechnen über den BuchungService
                            // (Diesen hast du als Feld in der CommandFactory: buchungService)
                            if (s != null && s.getKategorie() != null) {
                                summe += buchungService.calculateSeatPrice(s.getKategorie());
                            }
                        }
                        dto.setKategorie(cat != null ? cat : "-");
                        dto.setSumme(summe);

                        dtoList.add(dto);
                    }

                    return dtoList;
                });




            case "RESERVIERUNG_CANCEL":
                return new GenericCommand<String>(() -> {
                    Long reservierungId = ((Number) payload.get("reservierungId")).longValue();
                    Reservierung r = reservierungRepository.findById(reservierungId)
                            .orElseThrow(() -> new RuntimeException("Reservierung nicht gefunden"));

                    //  Sitze freigeben: Setze alle zugeordneten Sitze, die RESERVIERT sind, auf FREI
                    if (r.getReservierungSitze() != null) {
                        for (ReservierungSitz rs : r.getReservierungSitze()) {
                            if (rs.getSitz().getStatus() == Sitzstatus.RESERVIERT) {
                                rs.getSitz().setStatus(Sitzstatus.FREI);
                            }
                        }
                    }

                    // Reservierung auf "STORNIERT" setzen
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
                return new GenericCommand<List<VorstellungDTO>>(() -> {
                    System.out.println("=== [CommandFactory] Erstelle VORSTELLUNG_QUERY_ALL-Command ===");
                    List<Vorstellung> list = vorstellungRepository.findAll();
                    List<VorstellungDTO> dtos = list.stream().map(v -> {
                        VorstellungDTO dto = new VorstellungDTO();
                        dto.setId(v.getId());
                        dto.setFilmTitel(v.getFilmTitel());
                        dto.setStartzeit(v.getStartzeit() != null ? v.getStartzeit().toString() : "");
                        dto.setDauerMinuten(v.getDauerMinuten());
                        // Hier die Saal-ID setzen:
                        dto.setSaalId(v.getSaal() != null ? v.getSaal().getId() : null);
                        return dto;
                    }).collect(Collectors.toList());
                    return dtos;
                });



            case "VORSTELLUNG_VERFUEGBAR":
                return new GenericCommand<Map<String, Integer>>(() -> {
                    System.out.println("=== [CommandFactory] Erstelle VORSTELLUNG_VERFUEGBAR-Command ===");
                    Long id = ((Number) payload.get("id")).longValue();
                    String kategorie = (String) payload.get("kategorie"); // Kategorie aus dem Payload
                    return vorstellungService.getFreiePlaetze(id, kategorie);
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
