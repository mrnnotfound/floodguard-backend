package com.floodguard.service;

import com.floodguard.dto.LeituraChuvaRequest;
import com.floodguard.exception.RecursoNaoEncontradoException;
import com.floodguard.model.LeituraChuva;
import com.floodguard.model.Regiao;
import com.floodguard.repository.LeituraChuvaRepository;
import com.floodguard.repository.RegiaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeituraChuvaService {

    private final LeituraChuvaRepository repository;
    private final RegiaoRepository regiaoRepository;

    public LeituraChuvaService(
            LeituraChuvaRepository repository,
            RegiaoRepository regiaoRepository) {

        this.repository = repository;
        this.regiaoRepository = regiaoRepository;
    }

    public LeituraChuva registrar(LeituraChuvaRequest request) {

        Regiao regiao = regiaoRepository.findById(request.getRegiaoId())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Região não encontrada"));

        LeituraChuva leitura = new LeituraChuva();
        leitura.setRegiao(regiao);
        leitura.setChuvaPorHora(request.getChuvaPorHora());
        leitura.setFonte(request.getFonte());
        leitura.setDataHora(LocalDateTime.now());

        return repository.save(leitura);
    }

    public List<LeituraChuva> listarPorRegiao(Long regiaoId) {
        return repository.findByRegiaoIdOrderByDataHoraDesc(regiaoId);
    }
}