package com.kino.controller;

import com.kino.dto.SaalDTO;
import com.kino.dto.SaeleMitVorstellungenDTO;
import com.kino.dto.VorstellungDTO;
import com.kino.entity.Saal;
import com.kino.messaging.AsyncCommandSender;
import com.kino.service.SaalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/saal")
@CrossOrigin()
public class SaalController {

    @Autowired
    private SaalService saalService;

    @GetMapping
    @CrossOrigin
    public List<Saal> getAllSäle() {
        return saalService.getAllSäle();
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public Optional<Saal> getSaalById(@PathVariable Long id) {
        return saalService.getSaalById(id);
    }

    /**
     * POST: Neuen Saal anlegen.
     * Das JSON erwartet das Format:
     * {
     *   "command": "SAAL_WRITE",
     *   "payload": {
     *       "name": "Saal 1",
     *       "anzahlReihen": 3,
     *       "istFreigegeben": true,
     *       "sitzreihen": [ ... ]
     *   }
     * }
     */
    @PostMapping("/anlegen")
    @CrossOrigin
    public ResponseEntity<?> createSaal(@RequestBody Map<String, Object> requestBody) {
        try {
            // Sende den Command asynchron an RabbitMQ
            AsyncCommandSender.sendCommand("SAAL_WRITE", (Map<String, Object>) requestBody.get("payload"));
            // Rückmeldung, dass der Command gesendet wurde.
            Map<String, String> response = new HashMap<>();
            response.put("success", "true");
            response.put("message", "Saal-Anlage-Command gesendet.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", e.getMessage()));
        }
    }


    //  Alle Säle + Vorstellungen
    @GetMapping("/mitVorstellungen")
    @CrossOrigin
    public ResponseEntity<List<SaeleMitVorstellungenDTO>> getSaeleMitVorstellungen() {
        List<Saal> saele = saalService.getAllSaeleMitVorstellungen();

        // Umwandeln in DTOs
        List<SaeleMitVorstellungenDTO> dtos = saele.stream().map(saal -> {
            // VorstellungDTOs erzeugen
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

        return ResponseEntity.ok(dtos);
    }
}
