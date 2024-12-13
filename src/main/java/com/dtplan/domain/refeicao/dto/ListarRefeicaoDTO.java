package com.dtplan.domain.refeicao.dto;

import com.dtplan.domain.alimento.Alimento;
import com.dtplan.domain.alimento.dto.ListarAlimentosDTO;
import com.dtplan.domain.exercicio.Exercicio;
import com.dtplan.domain.refeicao.Refeicao;
import com.dtplan.domain.refeicao.RefeicaoAlimento;

import java.util.List;

public record ListarRefeicaoDTO(
        Long id,
        String descricao,

        Long dietaId,

        List<RefeicaoAlimento> refeicaoAlimentos
) {
    public ListarRefeicaoDTO(Refeicao refeicao) {
        this(
                refeicao.getId(),
                refeicao.getDescricao(),

                refeicao.getDieta().getId(),

                refeicao.getRefeicaoAlimentos().stream().toList()
        );
    }
}