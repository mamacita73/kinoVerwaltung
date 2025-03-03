package com.kino.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AsyncCommandSender {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private static final String QUEUE_NAME = "asyncCommandQueue";

    public AsyncCommandSender(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendCommand(String commandType, Map<String, Object> payload) {
        try {
            System.out.println("=== [AsyncCommandSender] Sende Command ===");
            System.out.println("CommandType: " + commandType);
            System.out.println("Payload: " + payload);
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("command", commandType);
            messageMap.put("payload", payload);
            String message = objectMapper.writeValueAsString(messageMap);
            rabbitTemplate.convertAndSend(QUEUE_NAME, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
