package com.kino.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

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
    public static void sendLoginRequest(String email) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {


            channel.queueDeclare("queue", false, false, false, null);
            channel.basicPublish("", "queue", null, email.getBytes());
        }
    }


    public static void main(String[] argv) throws Exception {
        String email = "nadja@fhdw.de";
        sendLoginRequest(email);
    }

}
