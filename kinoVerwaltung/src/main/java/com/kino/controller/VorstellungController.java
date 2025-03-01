package com.kino.controller;

import com.kino.dto.VorstellungDTO;
import com.kino.entity.Saal;
import com.kino.entity.Vorstellung;
import com.kino.messaging.AsyncCommandSender;
import com.kino.repository.SaalRepository;
import com.kino.service.VorstellungService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vorstellung")
public class VorstellungController {
    @Autowired
    private VorstellungService vorstellungService;

    @Autowired
    private SaalRepository saalRepository;
    /**
     * POST: Neue Vorstellung anlegen.
     * Erwartet ein JSON-Objekt wie:
     * {
     *   "filmTitel": "Film X",
     *   "startzeit": "16:00",
     *   "dauerMinuten": 90,
     *   "saalId": 1
     * }
     */
    @PostMapping("/anlegen")
    @CrossOrigin
    public ResponseEntity<VorstellungDTO> createVorstellung(@RequestBody VorstellungDTO dto) {
        Vorstellung saved = vorstellungService.anlegenVorstellung(
                dto.getFilmTitel(),
                dto.getStartzeit(),
                dto.getDauerMinuten(),
                dto.getSaalId()
        );

        // DTO für die Antwort erstellen
        VorstellungDTO responseDto = new VorstellungDTO(
                saved.getId(),
                saved.getSaal().getId(),
                saved.getFilmTitel(),
                saved.getStartzeit().toString(),  // LocalTime in String
                saved.getDauerMinuten()
        );

        return ResponseEntity.ok(responseDto);
    }


     /**
     * GET: Alle Vorstellungen abrufen.
     */
    @GetMapping
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
}