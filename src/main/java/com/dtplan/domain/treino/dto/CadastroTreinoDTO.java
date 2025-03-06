package com.dtplan.domain.treino.dto;

import com.dtplan.domain.usuario.Usuario;

public record CadastroTreinoDTO(
		String nome,
		String descricao,
		String autor,
		String usuario
) {
}