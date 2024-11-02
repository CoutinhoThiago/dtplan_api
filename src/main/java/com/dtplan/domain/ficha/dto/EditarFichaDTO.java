package com.dtplan.domain.ficha.dto;

import java.util.List;

public record EditarFichaDTO(
        String nome,
        Long treino,
        List<Long> exercicios
) {

}
