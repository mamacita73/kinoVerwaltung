package com.kino.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiVorstellungenDTO {
    private String filmTitel;
    private List<Long> saalIds;       // Liste der ausgewählten Säle
    private List<String> startzeiten; // Liste der Startzeiten (im Format "HH:mm")
    private int dauerMinuten;         // (Falls für alle Vorstellungen gleich)
}