package com.dtplan.domain.usuario;

import com.dtplan.domain.usuario.dto.CadastrarUsuarioDTO;
import com.dtplan.domain.usuario.dto.DetalharUsuarioDTO;
import com.dtplan.domain.usuario.dto.EditarUsuarioDTO;
import com.dtplan.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;

    public ResponseEntity cadastrarUsuario(CadastrarUsuarioDTO dados) {
        if(this.usuarioRepository.findByEmail(dados.email())  != null) {
            String mensagemErro = "Usuário com o login '" + dados.email() + "' já cadastrado.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagemErro);
        }
        else {
            String senhaEncriptada = new BCryptPasswordEncoder().encode(dados.senha());
            Permissao permissao = dados.permissao() != null ? Permissao.valueOf(String.valueOf(dados.permissao())) : Permissao.ADMIN;

            var usuario = new Usuario(
                    dados.email(),
                    senhaEncriptada,
                    permissao,
                    dados.nome(),
                    dados.cpf(),
                    dados.tipoUsuario(),
                    dados.cref(),
                    dados.crn()
            );
            usuarioRepository.save(usuario);

            var dto = new DetalharUsuarioDTO(usuario);
            return ResponseEntity.ok(dto); //return ResponseEntity.status(HttpStatus.OK).body("Cadastro realizado com sucesso.");
        }
    }

    public DetalharUsuarioDTO detalharUsuario(String authorizationHeader, EditarUsuarioDTO dados) {
        String token = authorizationHeader.substring(7); // Remover "Bearer "
        String usuarioLogin = tokenService.getSubject(token);
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(usuarioLogin);

        //usuario.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DetalharUsuarioDTO(usuario)).getBody();
    }

    public ResponseEntity<?> detalharUsuario(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido ou ausente.");}
        String token = authorizationHeader.substring(7); // Remove "Bearer "
        String login = tokenService.getSubject(token);
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(login);

        if (usuario == null) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");}

        DetalharUsuarioDTO dto = new DetalharUsuarioDTO(usuario);

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity excluirUsuario(String authorizationHeader) {
        String token = authorizationHeader.substring(7); // Remover "Bearer "
        String usuarioLogin = tokenService.getSubject(token);
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(usuarioLogin);

        usuarioRepository.deleteById(usuario.getId());
        return ResponseEntity.ok("Usuário excluido com sucesso");
    }

    public DetalharUsuarioDTO atualizarDadosUsuario(String authorizationHeader, EditarUsuarioDTO dados) {
        String token = authorizationHeader.substring(7); // Remover "Bearer "
        String usuarioLogin = tokenService.getSubject(token);
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(usuarioLogin);

        usuario.atualizarDados(dados.nome(), dados.cpf(), dados.dataNascimento(), dados.altura(), dados.pesoAtual(), dados.nivelAtividade(), dados.objetivo());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(new DetalharUsuarioDTO(usuario)).getBody();
    }
}
