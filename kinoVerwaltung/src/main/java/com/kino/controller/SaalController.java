package com.kino.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kino.dto.SaalDTO;
import com.kino.dto.SaeleMitVorstellungenDTO;
import com.kino.dto.VorstellungDTO;
import com.kino.entity.Saal;
import com.kino.messaging.AsyncCommandSender;
import com.kino.service.SaalService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    private final SaalService saalService;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final AsyncCommandSender asyncCommandSender;

    //Kontruktor
    @Autowired
    public SaalController(
            SaalService saalService,
            RabbitTemplate rabbitTemplate,
            ObjectMapper objectMapper,
            AsyncCommandSender asyncCommandSender) {
        this.saalService = saalService;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper =  objectMapper;
        this.asyncCommandSender = asyncCommandSender;
    }


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
            asyncCommandSender.sendCommand("SAAL_WRITE", (Map<String, Object>) requestBody.get("payload"));
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


    /**
     * GET: Alle Säle + Vorstellungen via RPC-Aufruf über RabbitMQ.
     */
    @GetMapping("/mitVorstellungen")
    public ResponseEntity<?> getSaeleMitVorstellungen() {
        try {
            // Erstelle eine Message-Map mit Command und Payload (Payload kann leer sein)
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("command", "SAELE_MIT_VORSTELLUNGEN_QUERY");
            messageMap.put("payload", new HashMap<String, Object>());

            // Sende synchron den RPC-Aufruf an die Queue "rpcCommandQueue" (Default Exchange "")
            Object responseObj = rabbitTemplate.convertSendAndReceive("", "rpcCommandQueue", messageMap);
            if (responseObj == null) {
                throw new RuntimeException("Keine Antwort vom RPC erhalten!");
            }

            // responseObj wird als JSON-String erwartet
            String jsonResponse = responseObj.toString();

            // Deserialisiere die Antwort in eine Liste von SaeleMitVorstellungenDTO
            List<SaeleMitVorstellungenDTO> dtos = objectMapper.readValue(
                    jsonResponse, new TypeReference<List<SaeleMitVorstellungenDTO>>() {});

            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }
}
