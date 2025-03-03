package com.kino.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kino.messaging.AsyncCommandSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buchung")
@CrossOrigin
public class BuchungController {

    private final AsyncCommandSender asyncCommandSender;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public BuchungController(AsyncCommandSender asyncCommandSender,
                             RabbitTemplate rabbitTemplate,
                             ObjectMapper objectMapper) {
        this.asyncCommandSender = asyncCommandSender;
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Direktbuchung (ohne Reservierung)
     * Beispiel-Payload:
     * {
     *   "command": "BUCHUNG_WRITE",
     *   "payload": {
     *      "vorstellungId": 1,
     *      "kategorie": "LOGE",
     *      "anzahl": 2,
     *      "kundenEmail": "kunde@example.com"
     *   }
     * }
     */
    @PostMapping("/anlegen")
    public ResponseEntity<?> anlegen(@RequestBody Map<String, Object> requestBody) {
        try {
            // Senden des RPC-Aufrufs an die rpcCommandQueue
            Object responseObj = rabbitTemplate.convertSendAndReceive("", "rpcCommandQueue", requestBody);
            if (responseObj == null) {
                throw new RuntimeException("Keine Antwort vom RPC erhalten!");
            }
            // Konvertiere die Antwort (JSON-String) in eine Map
            String jsonResponse = responseObj.toString();
            Map<String, Object> result = objectMapper.readValue(
                    jsonResponse, new TypeReference<Map<String, Object>>() {});
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }

    /**
     * Reservierung in Buchung umwandeln
     * Beispiel-Payload:
     * {
     *   "command": "RESERVIERUNG_BUCHEN",
     *   "payload": {
     *      "reservierungId": 1
     *   }
     * }
     */
    @PostMapping("/reservierung")
    public ResponseEntity<Map<String, String>> reservierungZuBuchung(@RequestBody Map<String, Object> requestBody) {
        try {
            String commandType = (String) requestBody.get("command");
            Map<String, Object> payload = (Map<String, Object>) requestBody.get("payload");

            asyncCommandSender.sendCommand(commandType, payload);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Reservierung in Buchung umgewandelt (Command gesendet).");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> err = new HashMap<>();
            err.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(err);
        }
    }
}
