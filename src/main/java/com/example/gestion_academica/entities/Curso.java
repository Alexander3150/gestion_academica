package com.example.gestion_academica.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "curso")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Curso {

    @Id
    @Column(name = "cod_curso", length = 12, nullable = false)
    @Size(max = 12)
    private String codCurso;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotBlank @Size(max = 100)
    private String nombre;

    @Column(name = "creditos", nullable = false)
    @NotNull @Min(1) @Max(100) 
    private Short creditos;
}
