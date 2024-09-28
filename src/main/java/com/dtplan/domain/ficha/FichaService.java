package com.dtplan.domain.ficha;

import com.dtplan.domain.exercicio.Exercicio;
import com.dtplan.domain.exercicio.ExercicioRepository;
import com.dtplan.domain.ficha.dto.CadastrarFichaDTO;
import com.dtplan.domain.ficha.dto.DetalharFichaDTO;
import com.dtplan.domain.treino.Treino;
import com.dtplan.domain.treino.TreinoRepository;
import com.dtplan.domain.treino.dto.CadastroTreinoDTO;
import com.dtplan.domain.treino.dto.DadosDetalharTreinoDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    TreinoRepository treinoRepository;

    public DetalharFichaDTO cadastrarFicha(CadastrarFichaDTO dados) {
        List<Exercicio> exercicios = new ArrayList<>();
        for (Long id : dados.exercicios()) {
            Optional<Exercicio> exercicioOpt = exercicioRepository.findById(id);
            // Se o exercício existir, adiciona à lista
            exercicioOpt.ifPresent(exercicios::add);
        }

        Optional<Treino> treinoOpt = treinoRepository.findById(dados.treino());
        Treino treino = treinoOpt.get();

        Ficha ficha = new Ficha(dados.nome(), treino,  exercicios);
        fichaRepository.save(ficha);

        // Retorna o DTO detalhando a ficha criada
        return new DetalharFichaDTO(ficha.getId(), ficha.getNome(), ficha.getExercicios());
    }
}
