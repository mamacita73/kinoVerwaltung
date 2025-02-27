package com.kino.controller;

import com.kino.messaging.RabbitMQSender;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Authentifizierungs-Controller für die Benutzeranmeldung.
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
    private final RabbitMQSender rabbitMQSender;

    public AuthController(RabbitMQSender rabbitMQSender) {
        this.rabbitMQSender = rabbitMQSender;
    }

    /**
     * Login-Endpunkt, der eine Anfrage an RabbitMQ sendet.
     *
     * @param loginRequest JSON mit der Benutzer-E-Mail.
     * @return Antwort mit Erfolg & Benutzerrolle.
     */
    @PostMapping("/login")
    public void login(@RequestBody Map<String, String> loginRequest) throws IOException, TimeoutException {
        rabbitMQSender.sendLoginRequest(loginRequest.get("email"));
    }
}
