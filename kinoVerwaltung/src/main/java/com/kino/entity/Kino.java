package com.kino.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

    //Repräsentiert ein Kino mit mehreren Sälen
    @Entity
    @Table(name = "kino")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Kino {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private String adresse;

        @OneToMany(mappedBy = "kino", cascade = CascadeType.ALL)
        @JsonManagedReference  // Verwaltung der Beziehung: Kino gibt seine Säle aus
        private List<Saal> saele;
    }



