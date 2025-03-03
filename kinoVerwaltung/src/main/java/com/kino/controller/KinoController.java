package com.kino.controller;

import com.kino.entity.Kino;
import com.kino.entity.Saal;
import com.kino.service.KinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kino")
@CrossOrigin
public class KinoController {
    @Autowired
    private KinoService kinoService;

    @GetMapping
    public List<Kino> getAllKinos() {
        return kinoService.getAllKinos();
    }

    @GetMapping("/{id}")
    public Optional<Kino> getKinoById(@PathVariable Long id) {
        return kinoService.getKinoById(id);
    }

    @PostMapping
    public Kino createKino(@RequestBody Kino kino) {
        return kinoService.saveKino(kino);
    }

    @PutMapping("/{id}")
    public Kino updateKino(@PathVariable Long id, @RequestBody Kino kino) {
        return kinoService.updateKino(id, kino);
    }

    @DeleteMapping("/{id}")
    public void deleteKino(@PathVariable Long id) {
        kinoService.deleteKino(id);
    }

    @GetMapping("/{id}/saele")
    public List<Saal> getSaeleByKino(@PathVariable Long id) {
        return kinoService.getSaeleByKino(id);
    }
}