package com.dtplan.domain.treino;

import com.dtplan.domain.ficha.Ficha;
import com.dtplan.domain.ficha.FichaRepository;
import com.dtplan.domain.treino.dto.CadastroTreinoDTO;
import com.dtplan.domain.treino.dto.EditarTreinoDTO;
import com.dtplan.domain.treino.dto.DetalharTreinoDTO;
import com.dtplan.domain.treino.dto.ListarTreinoDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org. springframework. data. domain. Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class TreinoService {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private FichaRepository fichaRepository;

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

    @Transactional
    public DetalharTreinoDTO detalharTreno(long id) {

        Treino treino = treinoRepository.findById(id).orElseThrow(() -> new RuntimeException("Treino não encontrado"));
        List<Ficha> fichas = fichaRepository.findByTreinoId(treino.getId());

        DetalharTreinoDTO dto = new DetalharTreinoDTO(treino, fichas);

        return dto;
    }

    @Transactional
    public Page<ListarTreinoDTO> listarTrenos(Pageable paginacao) {
        var page = treinoRepository.findAll(paginacao).map(ListarTreinoDTO::new);

        return page;
    }
}