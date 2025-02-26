package com.kino.controller;

import com.kino.entity.Benutzer;
import com.kino.service.BenutzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Benutzer über HTTP aufrufen
 */


@RestController
@RequestMapping("/benutzer")
public class BenutzerController {
    @Autowired
    private BenutzerService benutzerService;

    @GetMapping("/{id}")
    public Optional<Benutzer> getBenutzerByEmail(@PathVariable String email) {
        return benutzerService.getBenutzerByEmail(email);
    }

    @PostMapping("/register")
    public Benutzer saveBenutzer(@RequestBody Benutzer benutzer) {
        return benutzerService.saveBenutzer(benutzer);
    }
}