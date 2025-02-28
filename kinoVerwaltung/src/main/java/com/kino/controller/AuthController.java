package com.kino.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Map<String, String> registerRequest) {
        try {
            String benutzername = registerRequest.get("benutzername");
            String email = registerRequest.get("email");
            String password = registerRequest.get("password");
            String role = registerRequest.get("role");

            System.out.println("Registrierungs-Anfrage erhalten: " + email);

            // JSON-Objekt für RabbitMQ erstellen
            Map<String, String> data = new HashMap<>();
            data.put("benutzername", benutzername);
            data.put("email", email);
            data.put("password", password);
            data.put("role", role);

            String message = objectMapper.writeValueAsString(data);

            // Nachricht an RabbitMQ senden & Antwort erhalten
            String responseString = (String) rabbitTemplate.convertSendAndReceive("", "registerQueue", message);

            // Antwort verarbeiten
            Map<String, String> responseMap = objectMapper.readValue(responseString, new TypeReference<Map<String, String>>() {});

            return ResponseEntity.ok(responseMap);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("success", "false"));
        }
    }



}
