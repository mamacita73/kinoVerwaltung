package com.kino.controller;

import com.kino.entity.Benutzer;
import com.kino.service.BenutzerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Benutzer über HTTP aufrufen
 */


@RestController
@RequestMapping("/benutzer")
public class BenutzerController {
    private final BenutzerService benutzerService;

    public BenutzerController(BenutzerService benutzerService) {
        this.benutzerService = benutzerService;
    }

    @GetMapping
    public List<Benutzer> getAlleBenutzer() {
        return benutzerService.getAlleBenutzer();
    }
}
