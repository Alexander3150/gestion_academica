package com.example.gestion_academica.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

import lombok.*;

@Entity
@Table(name = "estudiante")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Estudiante {

    @Id
    @Column(name = "carnet", length = 12, nullable = false)
    @Size(max = 12)
    private String carnet;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotBlank @Size(max = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    @NotBlank @Size(max = 100)
    private String apellido;

    @Column(name = "fecha_nacimiento", nullable = false)
    @NotNull @Past
    private LocalDate fechaNacimiento;
}
