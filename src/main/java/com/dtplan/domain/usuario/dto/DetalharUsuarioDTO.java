package com.dtplan.domain.usuario.dto;

import com.dtplan.domain.usuario.Usuario;

import java.util.Date;

public record DetalharUsuarioDTO(
        long id,
        String nome,
        String cpf,
        Date dataNascimento,
        double altura,
        double pesoAtual,
        Enum nivelAtividade,
        Enum objetivo
) {
    public DetalharUsuarioDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getDataNascimento(),
                usuario.getAltura(),
                usuario.getPesoAtual(),
                usuario.getNivelAtividade(),
                usuario.getObjetivo()
        );
    }
}
