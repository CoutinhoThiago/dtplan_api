package com.dtplan.domain.treino.dto;

import java.util.List;

import com.dtplan.domain.exercicio.Exercicio;

public record DadosAtualizacaoTreino(
		String descricao,
		String autor,
		String tipo
		) {
}
