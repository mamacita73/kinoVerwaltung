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
     * POST: Mehrfache Vorstellungen anlegen.
     * Erwartetes JSON-Format:
     * {
     *   "command": "VORSTELLUNG_MULTI_WRITE",
     *   "payload": {
     *       "filmTitel": "Film X",
     *       "saalIds": [1,2],
     *       "startzeiten": ["16:00", "18:00"],
     *       "dauerMinuten": 90
     *   }
     * }
     * Diese Operation wird asynchron über RabbitMQ gesendet.
     */
    @PostMapping("/anlegenMulti")
    public ResponseEntity<?> createMultiVorstellung(@RequestBody Map<String, Object> requestBody) {
        try {
            // Extrahiere den "payload"-Teil (falls vorhanden)
            Map<String, Object> payload = (Map<String, Object>) requestBody.get("payload");
            if (payload == null) {
                payload = requestBody;
            }
            // Sende den Command asynchron an RabbitMQ
            asyncCommandSender.sendCommand("VORSTELLUNG_MULTI_WRITE", payload);
            Map<String, String> response = Collections.singletonMap("message", "Multi-Vorstellungs-Command gesendet.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("message", e.getMessage()));
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
    public ResponseEntity<?> getVerfuegbarkeit(@PathVariable Long id) {
        try {
            // Erstelle eine Message-Map mit Command und Payload
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("command", "VORSTELLUNG_VERFUEGBAR");

            // Payload: Übergibt die Vorstellung-ID
            Map<String, Object> payload = new HashMap<>();
            payload.put("id", id);
            messageMap.put("payload", payload);

            // Sende synchron den RPC-Aufruf über RabbitTemplate an die Queue "rpcCommandQueue"
            Object responseObj = rabbitTemplate.convertSendAndReceive("", "rpcCommandQueue", messageMap);
            if (responseObj == null) {
                throw new RuntimeException("Keine Antwort vom RPC erhalten!");
            }

            // Die Antwort wird als JSON-String erwartet.
            String jsonResponse = responseObj.toString();
            // Deserialisiere in eine Map<String, Integer>
            Map<String, Integer> availability = objectMapper.readValue(
                    jsonResponse, new TypeReference<Map<String, Integer>>() {});

            return ResponseEntity.ok(availability);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

}