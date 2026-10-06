package com.floodguard.repository;

import com.floodguard.model.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

    List<Ocorrencia> findByDataHoraAfter(LocalDateTime data);

    long countByDataHoraAfter(LocalDateTime data);

    // Conta só as ocorrências DA REGIÃO informada (o Spring entende "RegiaoId" como regiao.id)
    long countByRegiaoIdAndDataHoraAfter(Long regiaoId, LocalDateTime data);
}