package com.floodguard.service;

import com.floodguard.dto.OcorrenciaRequest;
import com.floodguard.exception.RecursoNaoEncontradoException;
import com.floodguard.model.Ocorrencia;
import com.floodguard.model.Regiao;
import com.floodguard.repository.OcorrenciaRepository;
import com.floodguard.repository.RegiaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository repository;
    private final RegiaoRepository regiaoRepository;

    public OcorrenciaService(
            OcorrenciaRepository repository,
            RegiaoRepository regiaoRepository) {

        this.repository = repository;
        this.regiaoRepository = regiaoRepository;
    }

    public Ocorrencia criar(OcorrenciaRequest request) {

        Regiao regiao = regiaoRepository.findById(request.getRegiaoId())
                .orElseThrow(() ->
                        new RecursoNaoEncontradoException("Região não encontrada"));

        Ocorrencia ocorrencia = new Ocorrencia();

        ocorrencia.setRegiao(regiao);
        ocorrencia.setDescricao(request.getDescricao());
        ocorrencia.setLatitude(request.getLatitude());
        ocorrencia.setLongitude(request.getLongitude());
        ocorrencia.setDataHora(LocalDateTime.now());

        return repository.save(ocorrencia);
    }

    public List<Ocorrencia> listar() {
        return repository.findAll();
    }

    public long contarOcorrenciasRecentes(Long regiaoId) {
        LocalDateTime limite = LocalDateTime.now().minusHours(2);
        return repository.countByRegiaoIdAndDataHoraAfter(regiaoId, limite);
    }
}