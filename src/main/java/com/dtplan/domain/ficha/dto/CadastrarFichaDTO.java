package com.dtplan.domain.ficha.dto;

import com.dtplan.domain.exercicio.Exercicio;
import com.dtplan.domain.ficha.Ficha;
import com.dtplan.domain.treino.Treino;

import java.util.List;

public record CadastrarFichaDTO(
        String nome,
        Long treino,
        List<Long> exercicios
) {
}
