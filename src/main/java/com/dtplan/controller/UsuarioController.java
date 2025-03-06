package com.dtplan.controller;

import com.dtplan.domain.usuario.UsuarioService;
import com.dtplan.domain.usuario.dto.CadastrarUsuarioDTO;
import com.dtplan.domain.usuario.dto.DetalharUsuarioDTO;
import com.dtplan.domain.usuario.dto.EditarUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criar")
    //@Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody /*@Valid*/ CadastrarUsuarioDTO dados) {
        return usuarioService.cadastrarUsuario(dados);
    }

    @PutMapping("/editar")
    @Transactional
    //@SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DetalharUsuarioDTO> editarUsuario(@RequestHeader("Authorization") String authorizationHeader, @RequestBody EditarUsuarioDTO dados) {
        var dto = usuarioService.atualizarDadosUsuario(authorizationHeader, dados);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/excluir")
    @Transactional
    //@SecurityRequirement(name = "bearer-key")
    public ResponseEntity excluirUsuario(@RequestHeader("Authorization") String authorizationHeader) {
        var dto = usuarioService.excluirUsuario(authorizationHeader);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/detalhar")
    //@SecurityRequirement(name = "bearer-key")
    public ResponseEntity<ResponseEntity> detalharUsuario(@RequestHeader("Authorization") String authorizationHeader) {
        var dto = usuarioService.detalharUsuario(authorizationHeader);

        return ResponseEntity.ok(dto);
    }
}
