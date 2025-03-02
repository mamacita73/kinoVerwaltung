package com.kino.controller;


import com.kino.dto.MultiVorstellungenDTO;
import com.kino.dto.VorstellungDTO;
import com.kino.entity.Saal;
import com.kino.entity.Sitz;
import com.kino.entity.Sitzreihe;
import com.kino.entity.Vorstellung;
import com.kino.repository.SaalRepository;
import com.kino.repository.VorstellungRepository;
import com.kino.service.VorstellungService;

import kinoVerwaltung.Sitzstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private VorstellungRepository vorstellungRepository;
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
    @PostMapping("/anlegenMulti")
    @CrossOrigin
    public ResponseEntity<List<VorstellungDTO>> createMultiVorstellung(@RequestBody MultiVorstellungenDTO dto) {
        // Überprüfe, ob die Anzahl der Säle und Startzeiten übereinstimmt
        if(dto.getSaalIds().size() != dto.getStartzeiten().size()){
            return ResponseEntity.badRequest().build();
        }

        // Liste zum Sammeln der angelegten Vorstellungen
        List<Vorstellung> created = new ArrayList<>();

        // Für jeden Eintrag (Index i) einen neuen Vorstellung-Datensatz anlegen
        for (int i = 0; i < dto.getSaalIds().size(); i++) {
            Long saalId = dto.getSaalIds().get(i);
            String startzeit = dto.getStartzeiten().get(i);
            // Delegiere an den Service, der bereits eine Vorstellung anlegt
            Vorstellung v = vorstellungService.anlegenVorstellung(
                    dto.getFilmTitel(),
                    startzeit,
                    dto.getDauerMinuten(),
                    saalId
            );
            created.add(v);
        }

        // Konvertiere die erstellten Vorstellungen in DTOs (zum Beispiel)
        List<VorstellungDTO> responseDtos = created.stream()
                .map(v -> new VorstellungDTO(
                        v.getId(),
                        v.getSaal().getId(),
                        v.getFilmTitel(),
                        v.getStartzeit().toString(),
                        v.getDauerMinuten()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDtos);
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


    // Return how many free seats per category for the given Vorstellung
    @GetMapping("/{id}/verfügbar")
    @CrossOrigin
    public Map<String, Integer> getVerfügbarkeit(@PathVariable Long id) {
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