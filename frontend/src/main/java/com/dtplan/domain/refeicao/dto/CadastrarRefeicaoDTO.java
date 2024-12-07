package com.dtplan.domain.refeicao.dto;

import com.dtplan.domain.alimento.Alimento;

import java.util.List;

public record CadastrarRefeicaoDTO(
        String nome,
        Long dieta,
        List<Alimento> alimentos
) {}
