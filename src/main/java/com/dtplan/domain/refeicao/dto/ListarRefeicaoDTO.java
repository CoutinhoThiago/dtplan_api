package com.dtplan.domain.refeicao.dto;

import com.dtplan.domain.alimento.Alimento;
import com.dtplan.domain.alimento.dto.ListarAlimentosDTO;
import com.dtplan.domain.exercicio.Exercicio;
import com.dtplan.domain.refeicao.Refeicao;

import java.util.List;

public record ListarRefeicaoDTO(
        Long id,
        String nome,

        Long dietaId,

        List<Alimento> alimentos
) {
    public ListarRefeicaoDTO(Refeicao refeicao) {
        this(
                refeicao.getId(),
                refeicao.getNome(),

                refeicao.getDieta().getId(),

                refeicao.getAlimentos().stream().toList()
        );
    }
}