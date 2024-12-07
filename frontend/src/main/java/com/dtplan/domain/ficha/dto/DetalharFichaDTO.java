package com.dtplan.domain.ficha.dto;

import com.dtplan.domain.exercicio.Exercicio;
import com.dtplan.domain.ficha.Ficha;

import java.util.List;

public record DetalharFichaDTO(
		Long id,
		String nome,
		List<Exercicio> exercicios
) {

	public DetalharFichaDTO(Ficha ficha) {
		this(
				ficha.getId(),
				ficha.getNome(),
				ficha.getExercicios().stream().toList()
		);
	}
}
