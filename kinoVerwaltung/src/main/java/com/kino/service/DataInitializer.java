package com.kino.service;

import com.kino.entity.Kino;
import com.kino.repository.KinoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(KinoRepository kinoRepository) {
        return args -> {
            // Prüfen, ob Kino mit ID 1 bereits existiert
            Long kinoId = 1L;
            if (!kinoRepository.existsById(kinoId)) {
                // Falls nicht, neu anlegen
                Kino kino = new Kino();
                kino.setId(kinoId);
                kino.setName("Kino an der FHDW");
                kino.setAdresse("Freundallee 15, Hannover");
                kinoRepository.save(kino);

                System.out.println("Kino mit ID=1 wurde automatisch angelegt.");
            } else {
                System.out.println("Kino mit ID=1 existiert bereits.");
            }
        };
    }
}
