package com.kino.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuchungDTO {
    private String buchungsnummer;
    private String status;
    private int summe;
}