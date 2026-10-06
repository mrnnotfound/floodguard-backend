package com.floodguard.service;

import com.floodguard.model.NivelRisco;
import org.springframework.stereotype.Service;

@Service
public class RiscoService {

    public NivelRisco calcularRisco(
            double chuvaPorHora,
            int ocorrencias) {

        if (chuvaPorHora >= 50 && ocorrencias >= 3) {
            return NivelRisco.EMERGENCIA;
        }

        if (chuvaPorHora >= 30 || ocorrencias >= 3) {
            return NivelRisco.RISCO;
        }

        if (chuvaPorHora >= 15 || ocorrencias >= 1) {
            return NivelRisco.ATENCAO;
        }

        return NivelRisco.NORMAL;
    }

    public String gerarMensagem(
            NivelRisco nivel,
            double chuva,
            int ocorrencias) {

        return switch (nivel) {

            case NORMAL ->
                    "Não foram identificados riscos significativos de alagamento.";

            case ATENCAO ->
                    "Há possibilidade de alagamento devido às condições de chuva ou ocorrências registradas.";

            case RISCO ->
                    "Risco elevado de alagamento. Chuvas intensas ou ocorrências foram identificadas na região.";

            case EMERGENCIA ->
                    "Risco elevado de alagamento. Chuvas intensas foram identificadas na região. Existem "
                            + ocorrencias
                            + " ocorrências registradas próximas.";
        };
    }
}