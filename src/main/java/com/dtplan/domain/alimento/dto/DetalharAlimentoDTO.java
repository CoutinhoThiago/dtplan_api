package com.dtplan.domain.alimento.dto;

import com.dtplan.domain.alimento.Alimento;

public record DetalharAlimentoDTO (
        long id,
        String nome
) {

    public DetalharAlimentoDTO(Alimento dados) {
        this(
                dados.getId(),
                dados.getNome()
        );
    }
}
