package com.dtplan.domain.treino;

import com.dtplan.domain.treino.dto.CadastroTreinoDTO;
import com.dtplan.domain.treino.dto.DadosDetalharTreinoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    public CadastroTreinoDTO cadastrarTreino(CadastroTreinoDTO dados) {
        Treino treino = new Treino(dados);
        treinoRepository.save(treino);

        return new CadastroTreinoDTO(treino.getDescricao(), treino.getAutor(), treino.getTipo(), treino.getUsuario());
    }

}