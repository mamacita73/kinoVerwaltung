package com.kino.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


// Vorstellung-Entity: Repräsentiert eine Filmvorstellung
@Entity
@Table(name = "vorstellung")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vorstellung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filmTitel;

    @Column(nullable = false)
    private String startzeit;

    @Column(nullable = false)
    private int dauerMinuten;

    @ManyToOne
    @JoinColumn(name = "saal_id", nullable = false)
    private Saal saal;

    @OneToMany(mappedBy = "vorstellung", cascade = CascadeType.ALL)
    private List<Buchung> buchungen;
}
