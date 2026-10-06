package com.floodguard.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "leituras_chuva")
public class LeituraChuva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "regiao_id", nullable = false)
    private Regiao regiao;

    @Column(nullable = false)
    private double chuvaPorHora;

    @Column(length = 50)
    private String fonte;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    public LeituraChuva() {
    }

    public Long getId() {
        return id;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    public double getChuvaPorHora() {
        return chuvaPorHora;
    }

    public void setChuvaPorHora(double chuvaPorHora) {
        this.chuvaPorHora = chuvaPorHora;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}