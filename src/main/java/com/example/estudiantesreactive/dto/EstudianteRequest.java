package com.example.estudiantesreactive.dto;


import jakarta.validation.constraints.*;

public record EstudianteRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 120, message = "El nombre es demasiado largo")
        String nombre,

        @NotBlank(message = "La carrera es obligatoria")
        @Size(max = 100, message = "La carrera es demasiado larga")
        String carrera,

        @NotNull(message = "El semestre es obligatorio")
        @Min(value = 1, message = "El semestre mínimo es 1")
        @Max(value = 20, message = "El semestre máximo permitido es 20")
        Integer semestre
) {}
