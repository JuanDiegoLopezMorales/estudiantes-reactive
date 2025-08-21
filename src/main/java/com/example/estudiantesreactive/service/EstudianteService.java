package com.example.estudiantesreactive.service;


import com.example.estudiantesreactive.domain.Estudiante;
import com.example.estudiantesreactive.dto.EstudianteRequest;
import com.example.estudiantesreactive.repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EstudianteService {

    private final EstudianteRepository repo;

    public EstudianteService(EstudianteRepository repo) {
        this.repo = repo;
    }

    public Flux<Estudiante> listar(String q, String carrera, Integer semestre) {
        if (carrera != null && !carrera.isBlank()) return repo.findByCarreraIgnoreCase(carrera.trim());
        if (semestre != null) return repo.findBySemestre(semestre);
        if (q != null && !q.isBlank()) return repo.findByNombreContainingIgnoreCase(q.trim());
        return repo.findAll();
    }

    public Mono<Estudiante> obtener(Long id) {
        return repo.findById(id);
    }

    public Mono<Estudiante> crear(EstudianteRequest req) {
        Estudiante e = new Estudiante(
                null,
                req.nombre().trim(),
                req.carrera().trim(),
                req.semestre(),
                null // lo setea la BD con CURRENT_TIMESTAMP
        );
        return repo.save(e);
    }

    public Mono<Estudiante> actualizar(Long id, EstudianteRequest req) {
        return repo.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Estudiante no encontrado")))
                .flatMap(existing -> {
                    Estudiante actualizado = new Estudiante(
                            existing.id(),
                            req.nombre().trim(),
                            req.carrera().trim(),
                            req.semestre(),
                            existing.createdAt() // preservamos created_at
                    );
                    return repo.save(actualizado);
                });
    }

    public Mono<Void> eliminar(Long id) {
        return repo.deleteById(id);
    }
}
