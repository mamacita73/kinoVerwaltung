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

    /**
     * Listener für die "rpcCommandQueue".
     * Empfängt ein JSON-String, führt das Command aus und gibt ein JSON-Result zurück.
     */
    @RabbitListener(queues = "rpcCommandQueue")
    public String handleRpcCommand(String message) {
        System.out.println("=== [RpcCommandReceiver] Nachricht empfangen ===");
        System.out.println("Nachricht (String): " + message);
        try {
            // JSON -> Map
            Map<?, ?> msgMap = objectMapper.readValue(message, Map.class);

            String commandType = (String) msgMap.get("command");
            Map<String, Object> payload = (Map<String, Object>) msgMap.get("payload");

            // Command ausführen
            Command<?> cmd = commandFactory.createCommand(commandType, payload);
            Object result = cmd.execute();

            // result als JSON zurück
            return objectMapper.writeValueAsString(result);

        } catch (Exception e) {
            e.printStackTrace();
            // Fehler-Map bauen
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
