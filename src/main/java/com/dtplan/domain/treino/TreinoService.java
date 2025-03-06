package com.dtplan.domain.treino;

import com.dtplan.domain.ficha.Ficha;
import com.dtplan.domain.ficha.FichaRepository;
import com.dtplan.domain.fichaExercicio.FichaExercicioRepository;
import com.dtplan.domain.treino.dto.CadastroTreinoDTO;
import com.dtplan.domain.treino.dto.EditarTreinoDTO;
import com.dtplan.domain.treino.dto.DetalharTreinoDTO;
import com.dtplan.domain.usuario.Usuario;
import com.dtplan.domain.usuario.UsuarioRepository;
import com.dtplan.infra.security.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FichaRepository fichaRepository;

    @Autowired
    private FichaExercicioRepository fichaExercicioRepository;

    @Transactional
    public CadastroTreinoDTO cadastrarTreino(CadastroTreinoDTO dados) {

        Usuario usuario = usuarioRepository.findByEmail(dados.usuario());
        Treino treino = new Treino(dados.nome(), dados.descricao(), dados.autor(), usuario);

        treinoRepository.save(treino);

        return new CadastroTreinoDTO(treino.getNome(), treino.getDescricao(), treino.getAutor(), treino.getUsuario().getNome());
    }
    @Transactional
    public DetalharTreinoDTO editarTreno(long id, EditarTreinoDTO dados) {
        Optional<Treino> treinoOpt = treinoRepository.findById(id);
        Treino treino = treinoOpt.get();

        treino.atualizarInformacoes(dados);

        treinoRepository.save(treino);

        return new DetalharTreinoDTO(treino.getId(), treino.getNome(), treino.getDescricao(), treino.getAutor(), /*treino.getUsuario(),*/ null);
    }

    @Transactional
    public void excluirTreino(long id) {
        Optional<Treino> treinoOpt = treinoRepository.findById(id);
        Treino treino = treinoOpt.get();

        for (Ficha ficha : treino.getFichas()) {
            fichaExercicioRepository.deleteByFichaId(ficha.getId());  // Método para excluir as entradas em ficha_exercicio
        }

        fichaRepository.deleteAll(treino.getFichas());

        treinoRepository.deleteById(id);
    }
}