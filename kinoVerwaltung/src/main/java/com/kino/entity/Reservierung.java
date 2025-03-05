package com.kino.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "reservierung")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"benutzer", "reservierungSitze", "vorstellung"})
public class Reservierung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String reservierungsnummer;

    @Column(nullable = false)
    private String datum;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String kundenEmail;

    private String datumReservierung;  // Format "YYYY-MM-DD"

    // Falls Sie es brauchen:
    @Column(name = "vorstellung_id_fk")
    private Long vorstellungId;

    /**
     * Falls Sie keinen Benutzer-Objekt angeben wollen, machen Sie nullable = true.
     * Dann kann 'benutzer' in der DB NULL sein.
     */
    @ManyToOne
    @JoinColumn(name = "benutzer_id", nullable = true)  // oder false, wenn Sie IMMER ein Benutzerobjekt setzen
    private Benutzer benutzer;

    @ManyToOne
    @JoinColumn(name = "vorstellung_id", nullable = false)
    private Vorstellung vorstellung;

    @OneToMany(mappedBy = "reservierung", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservierungSitz> reservierungSitze;


}
