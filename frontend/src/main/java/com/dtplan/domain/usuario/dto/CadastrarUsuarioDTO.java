package com.dtplan.domain.usuario.dto;


import com.dtplan.domain.usuario.Permissao;

public record CadastrarUsuarioDTO(
        String email,
        String senha,
        Permissao permissao,
        String nome,
        String cpf

//        "dataNascimento": null,
//	      "altura": 0.0,
//        "pesoAtual": 0.0,
//        "nivelAtividade": null,
//        "objetivo": null
) {}
