package com.floodguard.dto;

import com.floodguard.model.NivelRisco;

public class RiscoResponse {

    private NivelRisco nivel;
    private double chuvaPorHora;
    private int ocorrenciasProximas;
    private String mensagem;

    public RiscoResponse(
            NivelRisco nivel,
            double chuvaPorHora,
            int ocorrenciasProximas,
            String mensagem) {

        this.nivel = nivel;
        this.chuvaPorHora = chuvaPorHora;
        this.ocorrenciasProximas = ocorrenciasProximas;
        this.mensagem = mensagem;
    }

    public NivelRisco getNivel() {
        return nivel;
    }

    public double getChuvaPorHora() {
        return chuvaPorHora;
    }

    public int getOcorrenciasProximas() {
        return ocorrenciasProximas;
    }

    public String getMensagem() {
        return mensagem;
    }
}