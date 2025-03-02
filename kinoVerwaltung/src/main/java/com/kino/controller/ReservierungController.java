package com.kino.controller;

import com.kino.dto.ReservierungDTO;
import com.kino.entity.Reservierung;
import com.kino.entity.Vorstellung;
import com.kino.repository.VorstellungRepository;
import com.kino.service.ReservierungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservierung")
public class ReservierungController {
    @Autowired
    private ReservierungService reservierungService;
    @Autowired
    private VorstellungRepository vorstellungRepository;


    @GetMapping
    @CrossOrigin
    public List<Vorstellung> getAllVorstellungen() {
        return vorstellungRepository.findAll();
    }

    // Endpoint zum Anlegen einer neuen Reservierung
    @PostMapping
    @CrossOrigin
    public ResponseEntity<ReservierungDTO> createReservierung(@RequestBody ReservierungDTO dto) {
        Reservierung saved = reservierungService.createReservierung(dto);

        String email = (saved.getBenutzer() != null) ? saved.getBenutzer().getEmail() : null;
        Long vorstellungId = (saved.getVorstellung() != null) ? saved.getVorstellung().getId() : null;


        // Baue das Antwort-DTO
        ReservierungDTO response = new ReservierungDTO(
                saved.getId(),
                saved.getReservierungsnummer(),
                saved.getDatum(),
                saved.getStatus(),
                email,
                vorstellungId
        );

        return ResponseEntity.ok(response);
    }

    // Endpoint zum Abrufen einer Reservierung per ID
    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<ReservierungDTO> getReservierung(@PathVariable Long id) {
        Reservierung reservierung = reservierungService.getReservierungById(id);

        String email = (reservierung.getBenutzer() != null)
                ? reservierung.getBenutzer().getEmail() : null;
        Long vorstellungId = (reservierung.getVorstellung() != null)
                ? reservierung.getVorstellung().getId() : null;

        ReservierungDTO response = new ReservierungDTO(
                reservierung.getId(),
                reservierung.getReservierungsnummer(),
                reservierung.getDatum(),
                reservierung.getStatus(),
                email,
                vorstellungId
        );
        return ResponseEntity.ok(response);
    }


    // Endpoint zum Stornieren einer Reservierung
    @PutMapping("/{id}/cancel")
    @CrossOrigin
    public ResponseEntity<String> cancelReservierung(@PathVariable Long id) {
        reservierungService.cancelReservierung(id);
        return ResponseEntity.ok("Reservierung " + id + " wurde storniert.");
    }

    // Optional: Alle Reservierungen eines Benutzers abrufen
    @GetMapping("/byBenutzer/{benutzerId}")
    @CrossOrigin
    public ResponseEntity<List<ReservierungDTO>> getReservierungenByBenutzer(@PathVariable Long benutzerId) {
        List<Reservierung> list = reservierungService.getReservierungenByBenutzerId(benutzerId);

        List<ReservierungDTO> dtos = list.stream().map(r -> {
            String email = (r.getBenutzer() != null) ? r.getBenutzer().getEmail() : null;
            Long vorId = (r.getVorstellung() != null) ? r.getVorstellung().getId() : null;

            return new ReservierungDTO(
                    r.getId(),
                    r.getReservierungsnummer(),
                    r.getDatum(),
                    r.getStatus(),
                    email,
                    vorId
            );
        }).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}