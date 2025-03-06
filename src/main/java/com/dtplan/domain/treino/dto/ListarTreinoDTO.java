package com.dtplan.domain.treino.dto;

import com.dtplan.domain.treino.Tipo;
import com.dtplan.domain.treino.Treino;
import com.dtplan.domain.usuario.Usuario;

public record ListarTreinoDTO(
		Long id,
		String nome,
		String descricao,
		String autor,
		String usuario
		//Tipo tipo
) {
	public ListarTreinoDTO(Treino treino) {
		this(
				treino.getId(),
				treino.getNome(),
				treino.getDescricao(),
				treino.getAutor(),
				treino.getUsuario().getNome()

				//treino.getTipo()
		);
	}
}