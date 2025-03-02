package com.kino.controller;


import com.kino.dto.VorstellungDTO;
import com.kino.entity.Saal;
import com.kino.entity.Sitz;
import com.kino.entity.Sitzreihe;
import com.kino.entity.Vorstellung;
import com.kino.messaging.AsyncCommandSender;
import com.kino.repository.SaalRepository;
import com.kino.repository.VorstellungRepository;
import com.kino.service.VorstellungService;

import kinoVerwaltung.Sitzstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vorstellung")
public class VorstellungController {

    @Autowired
    private VorstellungService vorstellungService;
    @Autowired
    private VorstellungRepository vorstellungRepository;
    @Autowired
    private SaalRepository saalRepository;


    // POST: Mehrfache Vorstellungen anlegen (asynchron über RabbitMQ)
    @PostMapping("/anlegenMulti")
    @CrossOrigin
    public ResponseEntity<?> createMultiVorstellung(@RequestBody Map<String, Object> requestBody) {
        try {
            // Erwartetes Format:
            // { "command": "VORSTELLUNG_MULTI_WRITE", "payload": { "filmTitel": "...", "saalIds": [...], "startzeiten": [...], "dauerMinuten": 120 } }
            AsyncCommandSender.sendCommand("VORSTELLUNG_MULTI_WRITE", (Map<String, Object>) requestBody.get("payload"));
            Map<String, String> response = Collections.singletonMap("message", "Multi-Vorstellungs-Command gesendet.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("message", e.getMessage()));
        }
    }


     /**
     * GET: Alle Vorstellungen abrufen.
     */
    @GetMapping
    @CrossOrigin
    public ResponseEntity<List<VorstellungDTO>> getAllVorstellungen() {
        List<Vorstellung> list = vorstellungService.getAllVorstellungen();
        List<VorstellungDTO> dtos = list.stream().map(v -> new VorstellungDTO(
                v.getId(),
                v.getSaal().getId(),
                v.getFilmTitel(),
                v.getStartzeit().toString(),
                v.getDauerMinuten()
        )).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }


    @GetMapping("/{id}/verfügbar")
    @CrossOrigin
    public Map<String, Integer> getVerfügbarkeit(@PathVariable Long id) {
        System.out.println("Abgefragte Vorstellung-ID: " + id);
        Vorstellung vorstellung = vorstellungRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vorstellung nicht gefunden, ID=" + id));

        Saal saal = vorstellung.getSaal();
        if (saal == null) {
            throw new RuntimeException("Vorstellung hat keinen Saal!");
        }

        // Initialize counters
        Map<String, Integer> availability = new HashMap<>();
        availability.put("PARKETT", 0);
        availability.put("LOGE", 0);
        availability.put("LOGE_SERVICE", 0);

        // Loop through all Sitzreihen and Sitze to count how many are free per Kategorie
        for (Sitzreihe sr : saal.getSitzreihen()) {
            for (Sitz sitz : sr.getSitze()) {
                if (sitz.getStatus().equals(Sitzstatus.FREI)) {
                    String catName = sitz.getKategorie().name();  // e.g. "PARKETT", "LOGE", ...
                    availability.put(catName, availability.get(catName) + 1);
                }
            }
        }

        return availability;
    }

}