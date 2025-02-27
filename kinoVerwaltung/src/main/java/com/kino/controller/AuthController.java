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
}
