package com.kino.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
// Saal-Entity: Repräsentiert einen Kinosaal
@Entity
@Table(name = "saal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Saal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int anzahlReihen;

    @Column(nullable = false)
    private boolean istFreigegeben = false;

    @ManyToOne
    @JoinColumn(name = "kino_id", nullable = false)
    private Kino kino;

    @OneToMany(mappedBy = "saal", cascade = CascadeType.ALL)
    private List<Vorstellung> vorstellungen;
}

