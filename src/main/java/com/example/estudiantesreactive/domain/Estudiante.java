package com.example.estudiantesreactive.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("estudiantes")
public record Estudiante(
        @Id
        Long id,
        String nombre,
        String carrera,
        Integer semestre,
        @Column("created_at")
        LocalDateTime createdAt
) {}
