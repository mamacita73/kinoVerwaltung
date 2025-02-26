package com.kino.controller;

import com.kino.entity.Vorstellung;
import com.kino.service.VorstellungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vorstellung")
public class VorstellungController {
    @Autowired
    private VorstellungService vorstellungService;

    @GetMapping("/film/{filmTitel}")
    public List<Vorstellung> getVorstellungByFilm(@PathVariable String filmTitel) {
        return vorstellungService.getVorstellungByFilmTitel(filmTitel);
    }
}