package com.dtplan.domain.refeicao;

import com.dtplan.domain.dieta.Dieta;
import com.dtplan.domain.dieta.DietaRepository;
import com.dtplan.domain.exercicio.Exercicio;
import com.dtplan.domain.exercicio.ExercicioRepository;
import com.dtplan.domain.ficha.Ficha;
import com.dtplan.domain.ficha.FichaRepository;
import com.dtplan.domain.ficha.dto.CadastrarFichaDTO;
import com.dtplan.domain.ficha.dto.DetalharFichaDTO;
import com.dtplan.domain.ficha.dto.EditarFichaDTO;
import com.dtplan.domain.ficha.dto.ListarFichaDTO;
import com.dtplan.domain.refeicao.dto.CadastrarRefeicaoDTO;
import com.dtplan.domain.refeicao.dto.DetalharRefeicaoDTO;
import com.dtplan.domain.refeicao.dto.EditarRefeicaoDTO;
import com.dtplan.domain.refeicao.dto.ListarRefeicaoDTO;
import com.dtplan.domain.treino.Treino;
import com.dtplan.domain.treino.TreinoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RefeicaoService {

    @Autowired
    RefeicaoRepository refeicoesRepository;

    @Autowired
    DietaRepository dietaRepository;

    public Page<ListarRefeicaoDTO> listarRefeicoes(Pageable paginacao) {
        return refeicoesRepository.findAll(paginacao).map(ListarRefeicaoDTO::new);
    }

    public DetalharRefeicaoDTO cadastrar(CadastrarRefeicaoDTO dados) {
        Dieta dieta = dietaRepository.findById(dados.dieta()).orElseThrow(() -> new RuntimeException("Dieta não encontrada!"));
        Refeicao refeicao = new Refeicao(dados.nome(), dieta, null);

        // Adiciona alimentos, se existirem
        if (dados.alimentos() != null) {
            refeicao.getAlimentos().addAll(dados.alimentos());
        }

        // Salva a refeição no repositório
        Refeicao refeicaoSalva = refeicoesRepository.save(refeicao);

        // Retorna um DTO com os detalhes da refeição criada
        return new DetalharRefeicaoDTO(refeicaoSalva);
    }

    public DetalharRefeicaoDTO detalharRefeicao(Long id) {
        // Busca a refeição pelo ID
        Optional<Refeicao> refeicaoOpt = refeicoesRepository.findById(id);
        if (refeicaoOpt.isPresent()) {
            return new DetalharRefeicaoDTO(refeicaoOpt.get());
        } else {
            throw new RuntimeException("Refeição não encontrada."); // ou use uma exceção personalizada
        }
    }

    public void editar(Long id, EditarRefeicaoDTO dados) {
        // Busca a refeição pelo ID
        Optional<Refeicao> refeicaoOpt = refeicoesRepository.findById(id);
        if (refeicaoOpt.isPresent()) {
            Refeicao refeicao = refeicaoOpt.get();
            // Atualiza os campos da refeição
            //refeicao.setNome(dados.descricao());
            // Atualize outros campos conforme necessário
            refeicoesRepository.save(refeicao);
        } else {
            throw new RuntimeException("Refeição não encontrada."); // ou use uma exceção personalizada
        }
    }

    public void deletar(Long id) {
        // Remove a refeição pelo ID
        if (refeicoesRepository.existsById(id)) {
            refeicoesRepository.deleteById(id);
        } else {
            throw new RuntimeException("Refeição não encontrada."); // ou use uma exceção personalizada
        }
    }
}
