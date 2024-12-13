package com.dtplan.domain.refeicao.dto;

import com.dtplan.domain.alimento.Alimento;
import com.dtplan.domain.exercicio.Exercicio;
import com.dtplan.domain.ficha.Ficha;
import com.dtplan.domain.refeicao.Refeicao;
import com.dtplan.domain.refeicao.RefeicaoAlimento;
import com.dtplan.domain.treino.Tipo;
import com.dtplan.domain.treino.Treino;
import com.dtplan.domain.usuario.Usuario;

import java.util.List;

public record DetalharRefeicaoDTO(
		long id,
		String descricao,
		Long dieta,

		List<RefeicaoAlimento> refeicaoAlimentos
) {

	public DetalharRefeicaoDTO(Refeicao refeicao) {
		this(
				refeicao.getId(),
				refeicao.getDescricao(),
				refeicao.getDieta().getId(),

				refeicao.getRefeicaoAlimentos().stream().toList()
        );
	}
}
