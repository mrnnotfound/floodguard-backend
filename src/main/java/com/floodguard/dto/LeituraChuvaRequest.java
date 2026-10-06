package com.floodguard.dto;

import jakarta.validation.constraints.NotNull;

public class LeituraChuvaRequest {

    @NotNull
    private Long regiaoId;

    @NotNull
    private Double chuvaPorHora;

    private String fonte;

    public Long getRegiaoId() {
        return regiaoId;
    }

    public void setRegiaoId(Long regiaoId) {
        this.regiaoId = regiaoId;
    }

    public Double getChuvaPorHora() {
        return chuvaPorHora;
    }

    public void setChuvaPorHora(Double chuvaPorHora) {
        this.chuvaPorHora = chuvaPorHora;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }
}