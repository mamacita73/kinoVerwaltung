package com.kino.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reservierung_sitz")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservierungSitz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reservierung_id")
    private Reservierung reservierung;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sitz_id")
    private Sitz sitz;
}
