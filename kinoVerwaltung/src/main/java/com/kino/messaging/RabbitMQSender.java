package com.kino.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


/**
 * fürs Senden von Nachrichten
 */

@Service
public class RabbitMQSender {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String queue, String message) {
        rabbitTemplate.convertAndSend(queue, message);
        System.out.println("Gesendet: " + message);
    }
}
