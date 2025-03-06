package com.dtplan.domain.fichaExercicio.dto;

import com.dtplan.domain.exercicio.dto.DetalharExercicioDTO;
import com.dtplan.domain.fichaExercicio.FichaExercicio;

import java.util.List;
import java.util.Optional;

public record FichaExercicioDTO(
		Long id,
		DetalharExercicioDTO exercicio,

		int series,
		double carga,
		//Integer repeticoes_min,
		//Integer repeticoes_max,

		int duracao_minutos,
		int intensidade
) {

	// Construtor para inicializar o DTO a partir de FichaExercicio
	public FichaExercicioDTO(FichaExercicio fichaExercicio) {
		this(

				fichaExercicio.getId(),
				new DetalharExercicioDTO(fichaExercicio.getExercicio()),

				fichaExercicio.getSeries(),
				fichaExercicio.getCarga(),

				fichaExercicio.getDuracao_minutos(),
				fichaExercicio.getIntensidade()
		);
	}
}