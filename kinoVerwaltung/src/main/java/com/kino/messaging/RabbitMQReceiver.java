package com.kino.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kino.messaging.DatabaseCommand;
import com.kino.entity.Benutzer;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

@Service
public class RabbitMQReceiver {
    private final DatabaseCommand databaseCommand;
    private final ObjectMapper objectMapper;

    public RabbitMQReceiver(DatabaseCommand databaseCommand, ObjectMapper objectMapper) {
        this.databaseCommand = databaseCommand;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "loginQueue")
    public String receiveLoginRequest(String message) throws IOException, TimeoutException {
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

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("queue", false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
        };
        channel.basicConsume("queue", true, deliverCallback, consumerTag -> { });
    }

}
