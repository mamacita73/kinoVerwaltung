package com.kino.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

// Benutzer-Entity: Repräsentiert einen Benutzer (Admin, Kunde etc.)
@Entity
@Table(name = "benutzer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Benutzer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String benutzername;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String passwort;

    @Column(nullable = false, length = 50)
    private String rolle;

    @OneToMany(mappedBy = "benutzer", cascade = CascadeType.ALL)
    private List<Buchung> buchungen;
}
