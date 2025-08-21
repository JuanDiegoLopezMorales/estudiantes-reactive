package com.example.estudiantesreactive.controller;

import com.example.estudiantesreactive.domain.Estudiante;
import com.example.estudiantesreactive.dto.EstudianteRequest;
import com.example.estudiantesreactive.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/academia/estudiantes")
public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    // Filtros: ?q= (nombre contiene), ?carrera=, ?semestre=
    @GetMapping
    public Flux<Estudiante> listar(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "carrera", required = false) String carrera,
            @RequestParam(value = "semestre", required = false) Integer semestre
    ) {
        return service.listar(q, carrera, semestre);
    }

    @GetMapping("/{id}")
    public Mono<Estudiante> obtener(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Estudiante> crear(@Valid @RequestBody EstudianteRequest req) {
        return service.crear(req);
    }

    @PutMapping("/{id}")
    public Mono<Estudiante> actualizar(@PathVariable Long id, @Valid @RequestBody EstudianteRequest req) {
        return service.actualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> eliminar(@PathVariable Long id) {
        return service.eliminar(id);
    }
}
