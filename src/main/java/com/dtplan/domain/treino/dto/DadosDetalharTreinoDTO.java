package com.dtplan.domain.treino.dto;

import com.dtplan.domain.ficha.Ficha;
import com.dtplan.domain.treino.Tipo;
import com.dtplan.domain.treino.Treino;
import com.dtplan.domain.usuario.Usuario;

import java.util.List;

public record DadosDetalharTreinoDTO(
		long id,
		String descricao,
		String autor,
		Tipo tipo,

		Usuario usuario,

		List<Ficha> fichas
) {

	public DadosDetalharTreinoDTO(Treino treino, List<Ficha> fichas) {
		this(
				treino.getId(),
				treino.getDescricao(),
				treino.getAutor(),
				treino.getTipo(),

				treino.getUsuario(),

				fichas.stream().toList()
        );
	}
}
