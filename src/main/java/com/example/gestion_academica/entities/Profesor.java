package com.example.gestion_academica.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

@Entity
@Table(name = "profesor")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Profesor {

    @Id
    @Column(name = "cod_profesor", length = 12, nullable = false)
    @Size(max = 12)
    private String codProfesor;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotBlank @Size(max = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    @NotBlank @Size(max = 100)
    private String apellido;

    @Column(name = "correo", nullable = false, length = 120, unique = true)
    @NotBlank @Email @Size(max = 120)
    private String correo;
}
