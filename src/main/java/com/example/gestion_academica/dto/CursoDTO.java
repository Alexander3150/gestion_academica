package com.example.gestion_academica.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CursoDTO {
    private String codCurso;
    private String nombre;
    private short creditos;
}
