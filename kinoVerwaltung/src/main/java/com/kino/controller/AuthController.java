package com.kino.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kino.entity.Benutzer;
import com.kino.entity.Rolle;
import com.kino.messaging.AsyncCommandSender;
import com.kino.service.AuthService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final AuthService authService;
    private final AsyncCommandSender asyncCommandSender; // <-- injiziert

    public AuthController(
            RabbitTemplate rabbitTemplate,
            ObjectMapper objectMapper,
            AuthService authService,
            AsyncCommandSender asyncCommandSender  // <-- injizieren
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.authService = authService;
        this.asyncCommandSender = asyncCommandSender;
    }

    /**
     * Login-Endpunkt: Sendet einen RPC-Aufruf über RabbitMQ an die "rpcCommandQueue",
     * um den Benutzer per E-Mail zu authentifizieren.
     *
     * Beispiel-Payload:
     * {
     *   "email": "user@example.com"
     * }
     *
     * @param loginRequest JSON mit der E-Mail.
     * @return JSON-Antwort mit Erfolg und Benutzerinformationen.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        try {
            String email = loginRequest.get("email");
            System.out.println("Empfangene E-Mail: " + email);

            // Erstelle eine Message-Map für den RPC-Aufruf
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("command", "LOGIN");
            Map<String, Object> payload = new HashMap<>();
            payload.put("email", email);
            messageMap.put("payload", payload);

            // Sende synchron den RPC-Aufruf an die "loginQueue"
            Object responseObj = rabbitTemplate.convertSendAndReceive("", "rpcCommandQueue", messageMap);
            if (responseObj == null) {
                throw new RuntimeException("Keine Antwort vom RPC erhalten!");
            }

            // responseObj wird als JSON-String erwartet
            String jsonResponse = responseObj.toString();
            // Deserialisiere die Antwort in eine Map
            Map<String, String> response = objectMapper.readValue(jsonResponse, new TypeReference<Map<String, String>>() {});

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("success", "false"));
        }
    }

    /**
     * Register-Endpunkt: Nimmt Registrierungsdaten entgegen und sendet asynchron einen
     * Befehl über RabbitMQ, um den Benutzer zu speichern.
     *
     * Beispiel-Payload:
     * {
     *   "benutzername": "tester",
     *   "email": "tester@fhdw.de",
     *   "passwort": "1234",
     *   "rolle": "KUNDE"
     * }
     *
     * @param registerRequest JSON mit den Registrierungsdaten.
     * @return JSON-Antwort mit Erfolgsmeldung.
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String> registerRequest) {
        Map<String, String> response = new HashMap<>();
        try {
            String benutzername = registerRequest.get("benutzername");
            String email = registerRequest.get("email");
            String passwort = registerRequest.get("passwort");
            String rolle = registerRequest.get("rolle");

            System.out.println("Registrierungs-Anfrage erhalten: " + email);

            // Erstelle das Payload als Map
            Map<String, Object> payload = new HashMap<>();
            payload.put("benutzername", benutzername);
            payload.put("email", email);
            payload.put("passwort", passwort);
            payload.put("rolle", rolle);

            // Sende den Command asynchron via RabbitMQ
            asyncCommandSender.sendCommand("BENUTZER_WRITE", payload);

            System.out.println("Registrierungs-Anfrage gesendet: " + email);

            response.put("success", "true");
            response.put("message", "Registrierung erfolgreich!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", "false");
            response.put("message", "Fehler bei der Registrierung.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
