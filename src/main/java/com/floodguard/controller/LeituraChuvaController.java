package com.floodguard.controller;

import com.floodguard.dto.LeituraChuvaRequest;
import com.floodguard.model.LeituraChuva;
import com.floodguard.service.LeituraChuvaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leituras-chuva")
@CrossOrigin(origins = "*")
public class LeituraChuvaController {

    private final LeituraChuvaService service;

    public LeituraChuvaController(LeituraChuvaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeituraChuva registrar(@Valid @RequestBody LeituraChuvaRequest request) {
        return service.registrar(request);
    }

    @GetMapping("/regiao/{regiaoId}")
    public List<LeituraChuva> listarPorRegiao(@PathVariable Long regiaoId) {
        return service.listarPorRegiao(regiaoId);
    }
}