package com.dtplan.domain.ficha;

import com.dtplan.domain.exercicio.Exercicio;
import com.dtplan.domain.exercicio.ExercicioRepository;
import com.dtplan.domain.ficha.dto.CadastrarFichaDTO;
import com.dtplan.domain.ficha.dto.DetalharFichaDTO;
import com.dtplan.domain.ficha.dto.EditarFichaDTO;
import com.dtplan.domain.ficha.dto.ListarFichaDTO;
import com.dtplan.domain.fichaExercicio.FichaExercicio;
import com.dtplan.domain.fichaExercicio.FichaExercicioRepository;
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
public class FichaService {

    @Autowired
    ExercicioRepository exercicioRepository;

    @Autowired
    FichaRepository fichaRepository;
    @Autowired
    FichaExercicioRepository fichaExercicioRepository;

    @Autowired
    TreinoRepository treinoRepository;

    @Transactional
    public DetalharFichaDTO cadastrarFicha(CadastrarFichaDTO dados) {
        List<FichaExercicio> exercicios = new ArrayList<>();
        if (dados.exercicios() != null && !dados.exercicios().isEmpty()) {
            for (Long id : dados.exercicios()) {
                Optional<FichaExercicio> exercicioOpt = fichaExercicioRepository.findById(id);
                // Se o exercício existir, adiciona à lista
                exercicioOpt.ifPresent(exercicios::add);
            }
        }

        Optional<Treino> treinoOpt = treinoRepository.findById(dados.treino());
        Treino treino = treinoOpt.get();

        Ficha ficha = new Ficha(dados.nome(), treino);
        fichaRepository.save(ficha);

        // Retorna o DTO detalhando a ficha criada
        return new DetalharFichaDTO(ficha);
    }

    @Transactional
    public DetalharFichaDTO editarFicha(long id, EditarFichaDTO dados) {
        Optional<Ficha> fichaOpt = fichaRepository.findById(id);
        if (fichaOpt.isEmpty()) {
            throw new RuntimeException("Ficha não encontrada");
        }

        Ficha ficha = fichaOpt.get();

        // Atualiza a lista de exercícios
        List<Exercicio> exercicios = new ArrayList<>();
        for (Long exercicioId : dados.exercicios()) {
            Optional<Exercicio> exercicioOpt = exercicioRepository.findById(exercicioId);
            exercicioOpt.ifPresent(exercicios::add);
        }

        ficha.atualizarInformacoes(dados.nome(), exercicios);

        // Salva a ficha atualizada
        fichaRepository.save(ficha);

        // Retorna o DTO detalhando a ficha editada
        return new DetalharFichaDTO(ficha);
    }

    public List<ListarFichaDTO> listarFichas(Long trinoId) {
        return fichaRepository.findByTreinoId(trinoId);
    }

    public DetalharFichaDTO detalharFicha(long id) {
        Ficha ficha = fichaRepository.findById(id).orElseThrow(() -> new RuntimeException("Ficha não encontrado"));

        return new DetalharFichaDTO(ficha);
    }

    @Transactional
    public void excluirFicha(Long id) {
        fichaExercicioRepository.deleteByFichaId(id);
        fichaRepository.deleteById(id);
    }
}
