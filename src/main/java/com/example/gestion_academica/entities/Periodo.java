package com.example.gestion_academica.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "periodo")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Periodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_periodo")
    private Long idPeriodo;               

    @Column(name = "ciclo", nullable = false)
    private Short ciclo;                 

    @Column(name = "semestre", length = 20, nullable = false)
    @NotBlank @Size(max = 20)
    private String semestre;              
}
