package com.dtplan.domain.treino;

import com.dtplan.domain.treino.dto.CadastroTreinoDTO;
import com.dtplan.domain.treino.dto.EditarTreinoDTO;
import com.dtplan.domain.treino.dto.DetalharTreinoDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    @Transactional
    public CadastroTreinoDTO cadastrarTreino(CadastroTreinoDTO dados) {
        Treino treino = new Treino(dados);

        treinoRepository.save(treino);

        return new CadastroTreinoDTO(treino.getDescricao(), treino.getAutor(), treino.getTipo(), treino.getUsuario());
    }
    @Transactional
    public DetalharTreinoDTO editarTreno(long id, EditarTreinoDTO dados) {
        Optional<Treino> treinoOpt = treinoRepository.findById(id);
        Treino treino = treinoOpt.get();

        treino.atualizarInformacoes(dados);

        treinoRepository.save(treino);

        return new DetalharTreinoDTO(treino.getId(), treino.getDescricao(), treino.getAutor(), treino.getTipo(), treino.getUsuario(), null);
    }
}