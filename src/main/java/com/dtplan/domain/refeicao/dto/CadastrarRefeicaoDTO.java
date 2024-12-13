package com.dtplan.domain.refeicao.dto;

import com.dtplan.domain.alimento.Alimento;
import com.dtplan.domain.refeicao.RefeicaoAlimento;

import java.util.List;

public record CadastrarRefeicaoDTO(
        String descricao,
        Long dieta,

        List<RefeicaoAlimento> refeicaoAlimentos
) {}
