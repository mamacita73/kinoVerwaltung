package com.kino.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Service
public class AsyncCommandSender {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String QUEUE_NAME = "asyncCommandQueue";

    /**
     * Sendet ein Kommando an den RabbitMQ-Server.
     * WICHTIG! als Übergabe muss ein commandType und ein payload welches als Map<String, Object> gespeichert sein muss,übergeben werden.
     * @param commandType Der CommandType muss aus der CommandFactory kommen.
     * @param payload Der Payload muss als Map<String, Object> übergeben werden.
     * @throws IOException
     * @throws TimeoutException
     */
    public static void sendCommand(String commandType, Map<String, Object> payload) throws IOException, TimeoutException {
        // Verbindung zum RabbitMQ-Server aufbauen
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // ggf. anpassen

        // Verbindung aufbauen und Channel erstellen
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // Nachricht erstellen als JSON
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("command", commandType);
            messageMap.put("payload", payload);

            //Umwandlung in String
            String message = objectMapper.writeValueAsString(messageMap);

            // Nachricht senden
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
        }
    }
}
