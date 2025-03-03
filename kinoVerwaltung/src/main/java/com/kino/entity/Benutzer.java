package com.kino.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Enumerated(EnumType.STRING)
    private Rolle rolle;

    @OneToMany(mappedBy = "benutzer", cascade = CascadeType.ALL)
    private List<Buchung> buchungen;
}
