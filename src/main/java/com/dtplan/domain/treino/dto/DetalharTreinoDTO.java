package com.dtplan.domain.treino.dto;

import com.dtplan.domain.ficha.Ficha;
import com.dtplan.domain.ficha.dto.ListarFichaDTO;
import com.dtplan.domain.treino.Tipo;
import com.dtplan.domain.treino.Treino;
import com.dtplan.domain.usuario.Usuario;

import java.util.List;

public record DetalharTreinoDTO(
		long id,
		String nome,
		String descricao,
		String autor,
		//Tipo tipo,

		//Usuario usuario,

		List<ListarFichaDTO> fichas
) {

	public DetalharTreinoDTO(Treino treino, List<ListarFichaDTO> fichas) {
		this(
				treino.getId(),
				treino.getNome(),
				treino.getDescricao(),
				treino.getAutor(),
				//treino.getTipo(),

				//treino.getUsuario(),

				fichas
        );
	}
}
