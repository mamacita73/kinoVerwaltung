package com.kino.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "sitzreihe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sitzreihe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // z.B. "A", "B", "C"
    @Column(nullable = false)
    private String reihenBezeichnung;

    @Column(nullable = false)
    private int anzahlSitze;

    @ManyToOne
    @JoinColumn(name = "saal_id", nullable = false)
    private Saal saal;

    // Beziehung zu Sitz
    @OneToMany(mappedBy = "sitzreihe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Sitz> sitze;
}
