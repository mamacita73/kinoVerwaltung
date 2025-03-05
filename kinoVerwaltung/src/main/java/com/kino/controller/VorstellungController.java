package com.kino.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kino.dto.MultiVorstellungenDTO;
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
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vorstellung")
@CrossOrigin
public class VorstellungController {



    private final VorstellungRepository vorstellungRepository;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final AsyncCommandSender asyncCommandSender;
    private final VorstellungService vorstellungService; // Neu: Service direkt injizieren

    @Autowired
    public VorstellungController(VorstellungRepository vorstellungRepository,
                                 RabbitTemplate rabbitTemplate,
                                 ObjectMapper objectMapper,
                                 AsyncCommandSender asyncCommandSender,
                                 VorstellungService vorstellungService) { // VorstellungsService hier hinzufügen
        this.vorstellungRepository = vorstellungRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.asyncCommandSender = asyncCommandSender;
        this.vorstellungService = vorstellungService;
    }


    /**
     * POST /reservierung/anlegen
     * Beispiel-Payload:
     * {
     *   "command": "RESERVIERUNG_WRITE",
     *   "payload": {
     *     "vorstellungId": 1,
     *     "kategorie": "LOGE",
     *     "anzahl": 2,
     *     "kundenEmail": "admin@fhdw.de",
     *     "datum": "2025-03-02",
     *     "status": "RESERVIERT"
     *   }
     * }
     */
    @PostMapping("/anlegen")
    public ResponseEntity<Map<String, String>> createReservierung(@RequestBody Map<String, Object> requestBody) {
        try {
            Map<String, String> response = new HashMap<>();
            response.put("success", "true");
            response.put("message", "Reservierung erfolgreich");

            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(409).body(Collections.singletonMap("error", e.getMessage()));
        }
    }


    @PostMapping("/anlegenMulti")
    @CrossOrigin
    public ResponseEntity<?> anlegenMehrere(@RequestBody Map<String, Object> requestBody) {
        try {
            // Payload extrahieren
            Map<String, Object> payload = (Map<String, Object>) requestBody.get("payload");

            // Felder aus dem Payload auslesen
            String filmTitel = (String) payload.get("filmTitel");
            List<Long> saalIds = ((List<?>) payload.get("saalIds"))
                    .stream().map(obj -> ((Number) obj).longValue())
                    .collect(Collectors.toList());
            List<String> startzeiten = ((List<?>) payload.get("startzeiten"))
                    .stream().map(Object::toString)
                    .collect(Collectors.toList());
            int dauerMinuten = ((Number) payload.get("dauerMinuten")).intValue();

            // MultiVorstellungenDTO aufbauen
            MultiVorstellungenDTO dto = new MultiVorstellungenDTO(filmTitel, saalIds, startzeiten, dauerMinuten);

            // Synchroner Aufruf: Die Methode wirft hier ggf. eine Exception (z. B. bei Zeitüberschneidung)
            List<Vorstellung> result = vorstellungService.anlegenMehrereVorstellungen(dto);

            // Erfolgsmeldung, wenn keine Exception aufgetreten ist
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Vorstellungen wurden erfolgreich angelegt.");
            response.put("result", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            // Bei einer Exception (z. B. Zeitüberschneidung) wird der Fehler-Text zurückgegeben
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }


    /**
     * RPC-Endpoint: Alle Vorstellungen abrufen via RabbitMQ RPC.
     *
     * Es wird eine Nachricht an die Queue "rpcCommandQueue" gesendet mit dem Command-Typ "VORSTELLUNG_QUERY_ALL".
     * Der RPC-Listener verarbeitet diesen Command (z.B. in der CommandFactory) und gibt die Liste der Vorstellungen als JSON‑String zurück.
     */
    @GetMapping
    public ResponseEntity<?> getAllVorstellungen() {
        try {
            // Erstelle eine Message-Map, die den Command-Typ und einen leeren Payload enthält
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("command", "VORSTELLUNG_QUERY_ALL");
            messageMap.put("payload", new HashMap<String, Object>()); // Leerer Payload

            // Sende den RPC-Aufruf über RabbitTemplate an die Queue "rpcCommandQueue"
            Object responseObj = rabbitTemplate.convertSendAndReceive("", "rpcCommandQueue", messageMap);

            if (responseObj == null) {
                throw new RuntimeException("Keine Antwort vom RPC erhalten!");
            }

            // Die Antwort wird als JSON-String erwartet. Dieser JSON-String soll in eine Liste von VorstellungDTOs umgewandelt werden.
            String jsonResponse = responseObj.toString();
            List<VorstellungDTO> dtos = objectMapper.readValue(jsonResponse, new TypeReference<List<VorstellungDTO>>() {});

            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }


    /**
     * GET: Verfügbarkeitsabfrage für eine Vorstellung per RPC über RabbitMQ.
     * Erwartet einen Pfadparameter "id", der die ID der Vorstellung enthält.
     * Sendet einen RPC-Aufruf mit dem Command "VORSTELLUNG_VERFUEGBAR".
     */
    @GetMapping("/{id}/verfuegbar")
    public ResponseEntity<?> getVerfuegbarePlaetze(@PathVariable("id") Long id,
                                                   @RequestParam("kategorie") String kategorie) {
        try {
            // Nachricht bauen
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("command", "VORSTELLUNG_VERFUEGBAR");

            Map<String, Object> payload = new HashMap<>();
            payload.put("id", id);
            payload.put("kategorie", kategorie);
            messageMap.put("payload", payload);

            // RPC-Aufruf an die rpcCommandQueue
            Object responseObj = rabbitTemplate.convertSendAndReceive("", "rpcCommandQueue", messageMap);
            if (responseObj == null) {
                throw new RuntimeException("Keine Antwort vom RPC erhalten!");
            }

            // Antwort (JSON-String) in Map<String,Integer> umwandeln
            String jsonResponse = responseObj.toString();
            Map<String, Integer> verfuegbarePlaetze =
                    objectMapper.readValue(jsonResponse, new TypeReference<Map<String, Integer>>() {});
            return ResponseEntity.ok(verfuegbarePlaetze);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}