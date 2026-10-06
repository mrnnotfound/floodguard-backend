package com.floodguard.controller;

import com.floodguard.dto.OcorrenciaRequest;
import com.floodguard.model.Ocorrencia;
import com.floodguard.service.OcorrenciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ocorrencias")
@CrossOrigin(origins = "*")
public class OcorrenciaController {

    private final OcorrenciaService service;

    public OcorrenciaController(OcorrenciaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ocorrencia criar(@Valid @RequestBody OcorrenciaRequest request) {
        return service.criar(request);
    }

    @GetMapping
    public List<Ocorrencia> listar() {
        return service.listar();
    }
}