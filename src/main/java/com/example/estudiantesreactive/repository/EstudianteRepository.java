package com.example.estudiantesreactive.repository;

import com.example.estudiantesreactive.domain.Estudiante;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EstudianteRepository extends ReactiveCrudRepository<Estudiante, Long> {
    Flux<Estudiante> findByNombreContainingIgnoreCase(String nombre);
    Flux<Estudiante> findByCarreraIgnoreCase(String carrera);
    Flux<Estudiante> findBySemestre(Integer semestre);
}
