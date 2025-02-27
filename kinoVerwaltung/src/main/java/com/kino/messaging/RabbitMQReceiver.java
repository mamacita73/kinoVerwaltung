package com.kino.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kino.messaging.DatabaseCommand;
import com.kino.entity.Benutzer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RabbitMQReceiver {
    private final DatabaseCommand databaseCommand;
    private final ObjectMapper objectMapper;

    public RabbitMQReceiver(DatabaseCommand databaseCommand, ObjectMapper objectMapper) {
        this.databaseCommand = databaseCommand;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "loginQueue")
    public String receiveLoginRequest(String message) {
        try {
            Map<String, String> loginData = objectMapper.readValue(message, Map.class);
            String email = loginData.get("email");

            Optional<Benutzer> userOpt = databaseCommand.findUserByEmail(email);

            Map<String, String> response = new HashMap<>();

            if (userOpt.isPresent()) {
                response.put("success", "true");
                response.put("rolle", userOpt.get().getRolle().name());
            } else {
                response.put("success", "false");
            }

            return objectMapper.writeValueAsString(response);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"success\":\"false\"}";
        }
    }
}
