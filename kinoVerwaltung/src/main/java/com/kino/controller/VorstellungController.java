package com.kino.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final  ObjectMapper objectMapper;
    private final AsyncCommandSender asyncCommandSender;

    public VorstellungController(VorstellungRepository vorstellungRepository,
                                 RabbitTemplate rabbitTemplate,
                                 ObjectMapper objectMapper,
                                 AsyncCommandSender asyncCommandSender
                                 ) {
        this.vorstellungRepository = vorstellungRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;

        this.asyncCommandSender = asyncCommandSender;

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
    public ResponseEntity<Map<String, String>> anlegenMehrere(@RequestBody Map<String, Object> requestBody) {
        try {
            //  Extract commandType + payload
            String commandType = (String) requestBody.get("command");
            Map<String, Object> payload = (Map<String, Object>) requestBody.get("payload");

            // Senden Sie asynchron den Command
            asyncCommandSender.sendCommand(commandType, payload);

            // Erfolgsmeldung
            Map<String, String> response = new HashMap<>();
            response.put("message", "Mehrfache Vorstellungen angelegt (Command gesendet).");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> err = new HashMap<>();
            err.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(err);
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