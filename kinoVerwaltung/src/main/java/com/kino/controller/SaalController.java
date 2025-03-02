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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/saal")
@CrossOrigin()
public class SaalController {

    @Autowired
    private SaalService saalService;

    @GetMapping
    public List<Saal> getAllSäle() {
        return saalService.getAllSäle();
    }

    @GetMapping("/{id}")
    public Optional<Saal> getSaalById(@PathVariable Long id) {
        return saalService.getSaalById(id);
    }

    /**
     * POST: Neuen Saal anlegen.
     * Erwartet ein JSON im Format des SaalDTO, z.B.:
     * {
     *   "name": "Saal 1",
     *   "anzahlReihen": 3,
     *   "istFreigegeben": true
     * }
     */
    @PostMapping("/create")
    @CrossOrigin
    public ResponseEntity<SaalDTO> createSaal(@RequestBody SaalDTO dto) {
        // Saal-Entity aus dem DTO
        Saal saal = new Saal();
        saal.setName(dto.getName());
        saal.setAnzahlReihen(dto.getAnzahlReihen());
        saal.setIstFreigegeben(dto.isIstFreigegeben());
        //Hier kann noch ein Standard-Kino gesetzt werden (z. B. per Service)
        Saal saved = saalService.anlegenSaal(saal);

        // Konvertiere das gespeicherte Entity in ein DTO
        SaalDTO responseDto = new SaalDTO(
                saved.getId(),
                saved.getName(),
                saved.getAnzahlReihen(),
                saved.isIstFreigegeben()
        );
        return ResponseEntity.ok(responseDto);
    }


    //  Alle Säle + Vorstellungen
    @GetMapping("/mitVorstellungen")
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
