package com.kino.controller;


import com.kino.command.CommandFactory;
import com.kino.entity.Benutzer;
import com.kino.entity.Reservierung;
import com.kino.entity.Vorstellung;
import com.kino.messaging.AsyncCommandSender;
import com.kino.repository.BenutzerRepository;
import com.kino.repository.ReservierungRepository;
import com.kino.repository.VorstellungRepository;
import com.kino.service.ReservierungService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/reservierung")
@CrossOrigin
public class ReservierungController {
    private final ReservierungService reservierungService;
    private final VorstellungRepository vorstellungRepository;
    private final CommandFactory commandFactory;
    private final BenutzerRepository benutzerRepository;
    private final ReservierungRepository reservierungRepository;
    private final RabbitTemplate rabbitTemplate;  // kommt aus RabbitConfig
    private final AsyncCommandSender asyncCommandSender; // <-- neu injizieren

    // Konstruktor-Injektion aller benötigten Beans
    @Autowired
    public ReservierungController(
            ReservierungService reservierungService,
            VorstellungRepository vorstellungRepository,
            CommandFactory commandFactory,
            BenutzerRepository benutzerRepository,
            ReservierungRepository reservierungRepository,
            RabbitTemplate rabbitTemplate,
            AsyncCommandSender asyncCommandSender // <-- hier injizieren
    ) {
        this.reservierungService = reservierungService;
        this.vorstellungRepository = vorstellungRepository;
        this.commandFactory = commandFactory;
        this.benutzerRepository = benutzerRepository;
        this.reservierungRepository = reservierungRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.asyncCommandSender = asyncCommandSender; // <-- Instanz merken
    }

    @GetMapping
    public List<Vorstellung> getAllVorstellungen() {
        return vorstellungRepository.findAll();
    }

    // Endpoint zum Anlegen einer neuen Reservierung
    @PostMapping("/anlegen")
    public ResponseEntity<?> createReservierung(@RequestBody Map<String, Object> requestBody) {
        try {
            // E-Mail des aktuell eingeloggten Benutzers ermitteln
            String currentUserEmail = getCurrentUserEmail();
            Map<String, Object> payload = (Map<String, Object>) requestBody.get("payload");
            if (payload == null) {
                payload = new HashMap<>();
            }
            // Überschreibe oder setze kundenEmail im Payload
            payload.put("kundenEmail", currentUserEmail);
            requestBody.put("payload", payload);


            // Senden eines synchronen RPC-Aufrufs an die rpcCommandQueue
            Object responseObj = rabbitTemplate.convertSendAndReceive("", "rpcCommandQueue", requestBody);
            if (responseObj == null) {
                throw new RuntimeException("Keine Antwort vom RPC erhalten!");
            }

            // responseObj ist ein JSON-String, das nun ein 'Reservierung' -Objekt repräsentiert
            String jsonResponse = responseObj.toString();
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(409).body(Collections.singletonMap("error", e.getMessage()));
        }
    }


    /**
     * PUT /reservierung/{reservierungId}/cancel
     * Canceln einer Reservierung anhand der internen numerischen ID.
     * Im UI wird dennoch später die String-Reservierungsnummer angezeigt.
     */
    @PutMapping("/{reservierungId}/cancel")
    public ResponseEntity<String> cancelReservierungById(@PathVariable("reservierungId") Long reservierungId) {
        try {
            Map<String, Object> payload = new HashMap<>();
            // Hier wird die numerische ID übermittelt
            payload.put("reservierungId", reservierungId);
            asyncCommandSender.sendCommand("RESERVIERUNG_CANCEL", payload);
            return ResponseEntity.ok("Reservierungs-Cancellation-Command gesendet.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }


    // GET: Reservierungen eines Benutzers per RPC über RabbitMQ
    @GetMapping("/byEmail/{email}")
    public ResponseEntity<?> getReservierungenByBenutzer(@PathVariable String email) {
        try {
            // Command + Payload
            Map<String, Object> messageMap = new HashMap<>();
            messageMap.put("command", "RESERVIERUNG_QUERY_BY_EMAIL");

            Map<String, Object> payload = new HashMap<>();
            payload.put("kundenEmail", email);
            messageMap.put("payload", payload);

            // Senden eines synchronen RPC-Aufrufs an die rpcCommandQueue
            Object responseObj = rabbitTemplate.convertSendAndReceive(
                    "", // exchange = "" (Default Exchange)
                    "rpcCommandQueue", // RoutingKey = "rpcCommandQueue"
                    messageMap
            );

            if (responseObj == null) {
                throw new RuntimeException("Keine Antwort vom RPC erhalten!");
            }

            // responseObj ist i.d.R. ein String (JSON)
            String jsonResponse = responseObj.toString();

            return ResponseEntity.ok(jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(Collections.singletonMap("error", e.getMessage()));
        }
    }


    /**
     * Ermittelt die E-Mail des aktuell eingeloggten Benutzers aus dem HTTP-Header.
     */
    private String getCurrentUserEmail() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attr != null) {
            HttpServletRequest request = attr.getRequest();
            String email = request.getHeader("X-User-Email");
            if (email != null && !email.isEmpty()) {
                return email;
            }
        }
        throw new RuntimeException("Kein eingeloggter Benutzer gefunden.");
    }

}