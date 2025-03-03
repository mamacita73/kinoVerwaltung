package com.kino.controller;

import com.kino.messaging.AsyncCommandSender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buchung")
@CrossOrigin
public class BuchungController {

    private final AsyncCommandSender asyncCommandSender;

    public BuchungController(AsyncCommandSender asyncCommandSender) {
        this.asyncCommandSender = asyncCommandSender;
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
    public ResponseEntity<Map<String, String>> anlegen(@RequestBody Map<String, Object> requestBody) {
        try {
            String commandType = (String) requestBody.get("command");
            @SuppressWarnings("unchecked")
            Map<String, Object> payload = (Map<String, Object>) requestBody.get("payload");

            asyncCommandSender.sendCommand(commandType, payload);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Buchungs-Command gesendet.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> err = new HashMap<>();
            err.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(err);
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
