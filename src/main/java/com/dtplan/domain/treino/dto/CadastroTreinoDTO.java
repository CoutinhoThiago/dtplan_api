package com.dtplan.domain.treino.dto;

import com.dtplan.domain.ficha.Ficha;
import com.dtplan.domain.treino.Tipo;
import com.dtplan.domain.usuario.Usuario;

import java.util.List;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;

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
