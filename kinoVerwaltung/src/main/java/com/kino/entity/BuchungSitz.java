package com.kino.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "buchung_sitz")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuchungSitz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Verweis auf die zugehörige Buchung
    @ManyToOne
    @JoinColumn(name = "buchung_id", nullable = false)
    private Buchung buchung;

    // Verweis auf den Sitz
    @ManyToOne
    @JoinColumn(name = "sitz_id", nullable = false)
    private Sitz sitz;
}
