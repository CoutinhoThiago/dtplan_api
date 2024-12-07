package com.dtplan.domain.exercicio.dto;

import com.dtplan.domain.exercicio.Exercicio;

public record DetalharExercicioDTO(
		Long id,
		String nome,
		boolean ativo,
		Integer tipo,
		String observacoes,
		String musculo_alvo,
		Integer series,
		Integer repeticoes_min,
		Integer repeticoes_max,
		double carga,
		Integer intensidade,
		Integer duracao_minutos) {
	public DetalharExercicioDTO(Exercicio exercicio) {
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

				exercicio.getIntensidade(),
				exercicio.getDuracao_minutos()
				);
	}
}
