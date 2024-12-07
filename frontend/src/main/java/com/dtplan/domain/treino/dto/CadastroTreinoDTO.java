package com.dtplan.domain.treino.dto;

import com.dtplan.domain.treino.Tipo;
import com.dtplan.domain.usuario.Usuario;

public record CadastroTreinoDTO(
		//@NotBlank
		String descricao,
		//@NotBlank
		String autor,
		//@NotNull
		Tipo tipo,
		//@NotNull
		Usuario usuario
) { }
