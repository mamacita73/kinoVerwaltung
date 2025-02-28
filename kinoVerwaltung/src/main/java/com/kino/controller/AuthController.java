package com.kino.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kino.entity.Benutzer;
import com.kino.entity.Rolle;
import com.kino.messaging.AsyncCommandSender;
import com.kino.messaging.RabbitMQSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Authentifizierungs-Controller für die Benutzeranmeldung.
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public AuthController(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * Login-Endpunkt, der eine Anfrage an RabbitMQ sendet.
     *
     * @param loginRequest JSON mit der Benutzer-E-Mail.
     * @return Antwort mit Erfolg & Benutzerrolle.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        try {
            //Die E-Mail wird aus dem RequestBody extrahiert
            String email = loginRequest.get("email");
            System.out.println("Empfangene E-Mail4: " + email);


            //JSON-Objekt bauen
            Map<String, String> data = new HashMap<>();
            data.put("email", email);
            String message = objectMapper.writeValueAsString(data);

            //Synchrone Anfrage an RabbitMQ senden
            String responseString = (String) rabbitTemplate.convertSendAndReceive("", "loginQueue", message);

            //Antwort vom Listener zurückgeben
            Map<String, String> responseMap = objectMapper.readValue(responseString, new TypeReference<Map<String, String>>(){});

            return ResponseEntity.ok(responseMap);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("success", "false"));
        }
    }

    /**
     * Register-Endpunkt für neue Benutzer.
     * Erwartet z.B.:
     * {
     *   "benutzername": "tester",
     *   "email": "tester@fhdw.de",
     *   "passwort": "1234",
     *   "rolle": "KUNDE"
     * }
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String> registerRequest) {
        // JSON Antwort an frontend
        Map<String, String> response = new HashMap<>();
        try {
            String benutzername = registerRequest.get("benutzername");
            String email = registerRequest.get("email");
            String passwort = registerRequest.get("passwort");
            String rolle = registerRequest.get("rolle");

            System.out.println("Registrierungs-Anfrage erhalten: " + email);

            // Benutzer Objekt bauen
            Benutzer benutzer = new Benutzer();
            benutzer.setBenutzername(benutzername);
            benutzer.setEmail(email);
            benutzer.setPasswort(passwort);

            switch (rolle) {
                case "ADMIN":
                    benutzer.setRolle(Rolle.ADMIN);
                    break;
                case "KUNDE":
                    benutzer.setRolle(Rolle.KUNDE);
                    break;
                default:
                    // Standard setzen oder Fehler werfen
                    benutzer.setRolle(Rolle.KUNDE);
            }


            // Playload für RabbitMQ
            Map<String, Object> payload = new HashMap<>();
            payload.put("benutzername", benutzername);
            payload.put("email", email);
            payload.put("passwort", passwort);
            payload.put("rolle", rolle);

            // Nachricht an RabbitMQ senden
            AsyncCommandSender.sendCommand("BENUTZER_WRITE", payload);

            // Rückmeldung über Sendung
            System.out.println("Registrierungs-Anfrage gesendet: " + email);

            // 5) JSON-Antwort ans Frontend
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
