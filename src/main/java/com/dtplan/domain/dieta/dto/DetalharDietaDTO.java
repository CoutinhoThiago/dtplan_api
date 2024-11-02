package com.dtplan.domain.dieta.dto;

import com.dtplan.domain.dieta.Dieta;
import com.dtplan.domain.dieta.TipoDieta;
import com.dtplan.domain.usuario.Usuario;

public record DetalharDietaDTO(
		long id,
		String descricao,
		String autor,
		TipoDieta tipo,

		Usuario usuario
		) {

	public DetalharDietaDTO(Dieta dieta) {
		this(
				dieta.getId(),
				dieta.getDescricao(),
				dieta.getAutor(),
				dieta.getTipo(),

				dieta.getUsuario()
		);
	}
}
