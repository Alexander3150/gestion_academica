package com.example.gestion_academica.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "asignacion_profesor")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AsignacionProfesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion")
    private Long idAsignacion;  

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cod_curso", nullable = false,
            foreignKey = @ForeignKey(name = "fk_asig_curso"))
    private Curso curso;       

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_periodo", nullable = false,
            foreignKey = @ForeignKey(name = "fk_asig_periodo"))
    private Periodo periodo;    

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cod_profesor", nullable = false,
            foreignKey = @ForeignKey(name = "fk_asig_profesor"))
    private Profesor profesor;  
}
