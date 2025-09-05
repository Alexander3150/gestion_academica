package com.example.gestion_academica.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
    name = "inscripcion",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_insc_est_asig", columnNames = {"carnet", "id_asignacion"})
    },
    indexes = {
        @Index(name = "ix_insc_carnet", columnList = "carnet"),
        @Index(name = "ix_insc_id_asignacion", columnList = "id_asignacion")
    }
)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscripcion")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carnet", nullable = false,
            foreignKey = @ForeignKey(name = "fk_insc_estudiante"))
    private Estudiante estudiante;              

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_asignacion", nullable = false,
            foreignKey = @ForeignKey(name = "fk_insc_asignacion"))
    private AsignacionProfesor asignacion;     

    @Column(name = "nota_final", precision = 5, scale = 2)
    @Digits(integer = 3, fraction = 2)
    @DecimalMin("0.00") @DecimalMax("100.00")
    private BigDecimal notaFinal;              

    @Column(name = "fecha_evaluacion")
    private LocalDate fechaEvaluacion;         
}
