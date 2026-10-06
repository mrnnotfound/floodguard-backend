package com.floodguard.controller;

import com.floodguard.dto.RiscoResponse;
import com.floodguard.exception.RecursoNaoEncontradoException;
import com.floodguard.model.NivelRisco;
import com.floodguard.model.Regiao;
import com.floodguard.repository.RegiaoRepository;
import com.floodguard.service.AlertaService;
import com.floodguard.service.OcorrenciaService;
import com.floodguard.service.RiscoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regioes")
@CrossOrigin(origins = "*")
public class RegiaoController {

    private final RegiaoRepository repository;
    private final RiscoService riscoService;
    private final OcorrenciaService ocorrenciaService;
    private final AlertaService alertaService;

    public RegiaoController(
            RegiaoRepository repository,
            RiscoService riscoService,
            OcorrenciaService ocorrenciaService,
            AlertaService alertaService) {

        this.repository = repository;
        this.riscoService = riscoService;
        this.ocorrenciaService = ocorrenciaService;
        this.alertaService = alertaService;
    }

    @GetMapping
    public List<Regiao> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Regiao criar(@RequestBody Regiao regiao) {
        return repository.save(regiao);
    }

    @PutMapping("/{id}/risco")
    public RiscoResponse atualizarRisco(
            @PathVariable Long id,
            @RequestParam double chuvaPorHora) {

        Regiao regiao = repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Região não encontrada"));

        int ocorrencias = (int) ocorrenciaService.contarOcorrenciasRecentes(regiao.getId());

        NivelRisco risco = riscoService.calcularRisco(chuvaPorHora, ocorrencias);
        String mensagem = riscoService.gerarMensagem(risco, chuvaPorHora, ocorrencias);

        regiao.setChuvaPorHora(chuvaPorHora);
        regiao.setNivelRisco(risco);
        repository.save(regiao);

        if (risco != NivelRisco.NORMAL) {
            alertaService.criarAlerta(regiao, risco, mensagem);
        }

        return new RiscoResponse(risco, chuvaPorHora, ocorrencias, mensagem);
    }
}