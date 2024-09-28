package com.dtplan.domain.exercicio.dto;

import com.dtplan.domain.exercicio.Exercicio;
//import jakarta.validation.constraints.NotNull;

public record ListarExerciciosDTO(
		long id,
		String nome,
		boolean ativo,
		int tipo,
		String observacao,
		String musculo_alvo,
		int series,
		int repeticoes_min,
		int repeticoes_max,
		double carga,
		int duracao_minutos,
		int intensidade) {
	public ListarExerciciosDTO(Exercicio exercicio) {
		this(
				exercicio.getId(), 
				exercicio.getNome(),
				exercicio.isAtivo(),
				exercicio.getTipo(),
				exercicio.getObservacoes(),

				exercicio.getMusculo_alvo(),
				exercicio.getSeries(),
				exercicio.getRepeticoes_max(),
				exercicio.getRepeticoes_max(),
				exercicio.getCarga(),

				exercicio.getDuracao_minutos(),
				exercicio.getIntensidade()
				);
	}
}