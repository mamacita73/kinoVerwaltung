package com.kino.controller;

import com.kino.entity.Buchung;
import com.kino.service.BuchungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/buchung")
public class BuchungController {
    @Autowired
    private BuchungService buchungService;

    @GetMapping("/benutzer/{benutzerId}")
    public List<Buchung> getBuchungenByBenutzer(@PathVariable Long benutzerId) {
        return buchungService.getBuchungenByBenutzer(benutzerId);
    }
}