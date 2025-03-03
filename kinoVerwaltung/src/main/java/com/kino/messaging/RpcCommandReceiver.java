package com.kino.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kino.command.Command;
import com.kino.command.CommandFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RpcCommandReceiver {

    private final ObjectMapper objectMapper;
    private final CommandFactory commandFactory;

    public RpcCommandReceiver(ObjectMapper objectMapper, CommandFactory commandFactory) {
        this.objectMapper = objectMapper;
        this.commandFactory = commandFactory;
    }

    @RabbitListener(queues = "rpcCommandQueue")
    public String handleRpcCommand(Map<String, Object> msgMap) {
        System.out.println("=== [RpcCommandReceiver] Nachricht empfangen ===");
        System.out.println("Nachricht (Map): " + msgMap);
        try {
            String commandType = (String) msgMap.get("command");
            Map<String, Object> payload = (Map<String, Object>) msgMap.get("payload");
            System.out.println("=== [RpcCommandReceiver] Deserialisierte Nachricht: " + msgMap);
            System.out.println("=== [RpcCommandReceiver] Payload empfangen: " + payload);

            Command<?> cmd = commandFactory.createCommand(commandType, payload);
            Object result = cmd.execute();
            return objectMapper.writeValueAsString(result);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> errMap = new HashMap<>();
            errMap.put("error", e.getMessage());
            try {
                return objectMapper.writeValueAsString(errMap);
            } catch (Exception ex) {
                return "{\"error\":\"" + ex.getMessage() + "\"}";
            }
        }
    }
}
