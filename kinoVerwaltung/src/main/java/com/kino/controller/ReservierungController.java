package com.kino.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    // Konstruktor-Injektion aller benötigten Beans
    @Autowired
    public ReservierungController(
            ReservierungService reservierungService,
            VorstellungRepository vorstellungRepository,
            CommandFactory commandFactory,
            BenutzerRepository benutzerRepository,
            ReservierungRepository reservierungRepository,
            RabbitTemplate rabbitTemplate,
            AsyncCommandSender asyncCommandSender,
            ObjectMapper objectMapper // <-- hier injizieren
    ) {
        this.reservierungService = reservierungService;
        this.vorstellungRepository = vorstellungRepository;
        this.commandFactory = commandFactory;
        this.benutzerRepository = benutzerRepository;
        this.reservierungRepository = reservierungRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.asyncCommandSender = asyncCommandSender; // <-- Instanz merken
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<Vorstellung> getAllVorstellungen() {
        return vorstellungRepository.findAll();
    }

    // Endpoint zum Anlegen einer neuen Reservierung
    @PostMapping("/anlegen")
    public ResponseEntity<Map<String, String>> createReservierung(@RequestBody Map<String, Object> requestBody) {
        try {
            // E-Mail des aktuell eingeloggten Benutzers ermitteln
            String currentUserEmail = getCurrentUserEmail();
            Map<String, Object> payload = (Map<String, Object>) requestBody.get("payload");
            if (payload == null) {
                payload = new HashMap<>();
            }
            // Hier synchron den Service aufrufen:
            Long vorstellungId = ((Number) payload.get("vorstellungId")).longValue();
            String kategorie = (String) payload.get("kategorie");
            int anzahl = ((Number) payload.get("anzahl")).intValue();
            String kundenEmail = (String) payload.get("kundenEmail");
            String datum = (String) payload.get("datum");
            String status = (String) payload.get("status");
            Reservierung reservierung = reservierungService.reservierungAnlegen(
                vorstellungId, kategorie, anzahl, kundenEmail, datum, status
            );

            Map<String, String> response = new HashMap<>();
            response.put("success", "true");
            response.put("message", "Reservierung erfolgreich angelegt.");
            response.put("reservierungsnummer", reservierung.getReservierungsnummer());
            return ResponseEntity.ok(response);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(409).body(Collections.singletonMap("error", e.getMessage()));
        }
    }


    // PUT: Reservierung stornieren (Write-Command über RabbitMQ asynchron)
    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelReservierung(@PathVariable Long id) {
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("reservierungId", id);
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

    /**
     * GET /reservierung/byEmail/{email}
     *
     * Lädt alle Reservierungen eines Benutzers anhand seiner E-Mail,
     * ohne RabbitMQ (direkt per JPA-Abfrage).

    @GetMapping("/byEmail/{email}")
    public List<Reservierung> getReservierungenByEmail(@PathVariable String email) {
        // 1) Benutzer anhand E-Mail finden
        Benutzer benutzer = benutzerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden mit E-Mail=" + email));

        // 2) Alle Reservierungen dieses Benutzers laden
        return reservierungRepository.findByBenutzerId(benutzer.getId());
    }

     */
}