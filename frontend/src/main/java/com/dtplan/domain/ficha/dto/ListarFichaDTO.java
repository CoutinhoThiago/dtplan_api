package com.dtplan.domain.ficha.dto;

import com.dtplan.domain.exercicio.Exercicio;
import com.dtplan.domain.ficha.Ficha;

import java.util.List;

public record ListarFichaDTO(
    Long id,
    Long treinoId,
    String nome,
    List<Exercicio> exercicios
) {
    public ListarFichaDTO(Ficha ficha) {
        this(
                ficha.getId(),
                ficha.getTreino().getId(),
                ficha.getNome(),
                ficha.getExercicios().stream().toList()

        );
    }
}
