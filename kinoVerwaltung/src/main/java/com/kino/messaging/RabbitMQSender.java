package com.kino.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Klasse zum Senden von Nachrichten über RabbitMQ mit JSON-Serialisierung.
 */
@Service
public class RabbitMQSender {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public RabbitMQSender(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * Sendet eine Login-Anfrage mit nur der E-Mail-Adresse.
     *
     * @param email Benutzer-E-Mail
     * @return Login-Ergebnis (Map mit Erfolg & Rolle)
     */
    public Map<String, String> sendLoginRequest(String email) {
        try {
            // Erstelle das JSON-Objekt für den Login (ohne Passwort)
            Map<String, String> loginData = new HashMap<>();
            loginData.put("email", email);

            // In JSON umwandeln
            String jsonRequest = objectMapper.writeValueAsString(loginData);

            // Anfrage senden und Antwort erhalten
            String jsonResponse = (String) rabbitTemplate.convertSendAndReceive("loginQueue", jsonRequest);

            // JSON in Map umwandeln
            return objectMapper.readValue(jsonResponse, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
