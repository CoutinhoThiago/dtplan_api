package com.dtplan.domain.usuario.dto;

import java.util.Date;
import java.util.Optional;

public record EditarUsuarioDTO(
        String email,
        Optional<String> senha,
        Optional<String> nome,
        Optional<String> cpf,
        Optional<Date> dataNascimento,
        Optional<Integer> altura,
        Optional<Integer> pesoAtual,
        Optional<String> nivelAtividade,
        Optional<String> objetivo,
        Optional<String> tipoUsuario,
        Optional<String> cref,
        Optional<String> crn
) {}
