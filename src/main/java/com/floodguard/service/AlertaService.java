package com.floodguard.service;

import com.floodguard.model.Alerta;
import com.floodguard.model.NivelRisco;
import com.floodguard.model.Regiao;
import com.floodguard.repository.AlertaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertaService {

    private final AlertaRepository repository;

    public AlertaService(AlertaRepository repository) {
        this.repository = repository;
    }

    public Alerta criarAlerta(
            Regiao regiao,
            NivelRisco nivel,
            String mensagem) {

        Alerta alerta = new Alerta();

        alerta.setRegiao(regiao);

        alerta.setTitulo(
                nivel == NivelRisco.EMERGENCIA
                        ? "🚨 Risco elevado de alagamento"
                        : "⚠️ Alerta de alagamento"
        );

        alerta.setMensagem(mensagem);
        alerta.setNivelRisco(nivel);
        alerta.setDataHora(LocalDateTime.now());
        alerta.setLatitude(regiao.getLatitude());
        alerta.setLongitude(regiao.getLongitude());

        return repository.save(alerta);
    }

    public List<Alerta> listar() {
        return repository.findAll();
    }
}