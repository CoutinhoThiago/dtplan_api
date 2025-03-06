package com.dtplan.domain.ficha.dto;

import com.dtplan.domain.ficha.Ficha;
import com.dtplan.domain.fichaExercicio.dto.FichaExercicioDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record DetalharFichaDTO(
		Long id,
		Long treinoId,
		String nome,
		List<FichaExercicioDTO> exercicios
) {

	public DetalharFichaDTO(Ficha ficha) {
		this(
				ficha.getId(),
				ficha.getTreino().getId(),
				ficha.getNome(),
				ficha.getFichaExercicios() != null ?
						ficha.getFichaExercicios().stream()
								.map(FichaExercicioDTO::new) // Mapeia cada FichaExercicio para um FichaExercicioDTO
								.collect(Collectors.toList()) // Coleta os resultados em uma lista
						: new ArrayList<>() // Se for null, usa uma lista vazia
		);
	}
}
