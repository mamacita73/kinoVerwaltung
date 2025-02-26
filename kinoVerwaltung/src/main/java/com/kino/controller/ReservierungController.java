package com.kino.controller;

import com.kino.entity.Reservierung;
import com.kino.service.ReservierungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservierung")
public class ReservierungController {
    @Autowired
    private ReservierungService reservierungService;

    @GetMapping("/benutzer/{benutzerId}")
    public List<Reservierung> getReservierungenByBenutzer(@PathVariable String email) {
        return reservierungService.getReservierungenByBenutzer(email);
    }
}
