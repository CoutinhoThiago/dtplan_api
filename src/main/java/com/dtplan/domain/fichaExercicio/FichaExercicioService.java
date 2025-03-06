package com.dtplan.domain.fichaExercicio;

import com.dtplan.domain.exercicio.Exercicio;
import com.dtplan.domain.exercicio.ExercicioRepository;
import com.dtplan.domain.ficha.Ficha;
import com.dtplan.domain.ficha.FichaRepository;
import com.dtplan.domain.ficha.dto.DetalharFichaDTO;
import com.dtplan.domain.ficha.dto.EditarFichaDTO;
import com.dtplan.domain.ficha.dto.ListarFichaDTO;
import com.dtplan.domain.fichaExercicio.dto.CadastrarFichaExercicioDTO;
import com.dtplan.domain.fichaExercicio.dto.DetalharFichaExercicioDTO;
import com.dtplan.domain.treino.TreinoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FichaExercicioService {

    @Autowired
    ExercicioRepository exercicioRepository;

    @Autowired
    FichaRepository fichaRepository;

    @Autowired
    FichaExercicioRepository fichaExercicioRepository;

    @Autowired
    TreinoRepository treinoRepository;

    @Transactional
    public DetalharFichaExercicioDTO cadastrarFichaExercicio(CadastrarFichaExercicioDTO dados) {
        List<FichaExercicio> exercicios = new ArrayList<>();

        Optional<Exercicio> exercicioOpt = exercicioRepository.findById(dados.exercicioId());
        Exercicio exercicio = exercicioOpt.get();
        Optional<Ficha> fichaOpt = fichaRepository.findById(dados.fichaId());
        Ficha ficha = fichaOpt.get();

        FichaExercicio fichaExercicio = new FichaExercicio(
                ficha,
                exercicio,

                dados.repeticoes(),
                dados.series(),
                dados.carga(),
                //Integer repeticoes_min,
                //Integer repeticoes_max,

                dados.duracao_minutos(),
                dados.intensidade()
                );
        fichaExercicioRepository.save(fichaExercicio);

        // Retorna o DTO detalhando a ficha criada
        return new DetalharFichaExercicioDTO(fichaExercicio);
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

    public DetalharFichaExercicioDTO detalharFichaExercicio(long id) {
        FichaExercicio ficha = fichaExercicioRepository.findById(id).orElseThrow(() -> new RuntimeException("FichaExercicio não encontrado"));

        return new DetalharFichaExercicioDTO(ficha);
    }
}
