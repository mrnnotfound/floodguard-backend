package com.floodguard.repository;

import com.floodguard.model.LeituraChuva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeituraChuvaRepository extends JpaRepository<LeituraChuva, Long> {

    List<LeituraChuva> findByRegiaoIdOrderByDataHoraDesc(Long regiaoId);
}