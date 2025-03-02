package com.kino.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservierungDTO {
    private Long id;
    private String reservierungsnummer;
    private String datum;
    private String status;
    private String kundenEmail;
    private Long vorstellungId; // Die ID der Vorstellung, zu der diese Reservierung gehört

}