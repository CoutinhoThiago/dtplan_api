package com.dtplan.domain.alimento;

import com.dtplan.domain.alimento.dto.CadastrarAlimentoDTO;
import com.dtplan.domain.alimento.dto.DetalharAlimentoDTO;
import com.dtplan.domain.alimento.dto.ListarAlimentosDTO;
import com.dtplan.domain.dieta.Dieta;
import com.dtplan.domain.dieta.dto.CadastrarDietaDTO;
import com.dtplan.domain.exercicio.Exercicio;
import com.dtplan.domain.exercicio.dto.DetalharExercicioDTO;
import com.dtplan.domain.exercicio.dto.ListarExerciciosDTO;
import jakarta.transaction.Transactional;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    @Transactional
    public CadastrarAlimentoDTO cadastrarDieta(CadastrarAlimentoDTO dados) {
        Alimento alimento = new Alimento(dados.nome());

        alimentoRepository.save(alimento);

        return new CadastrarAlimentoDTO(alimento.getNome());
    }

    public Page<ListarAlimentosDTO> listarAlimentos(Pageable paginacao) {
        return alimentoRepository.findAll(paginacao).map(ListarAlimentosDTO::new);
    }

    public DetalharAlimentoDTO detalharAlimento(long id) {
        Alimento alimento = alimentoRepository.getReferenceById(id);
        return new DetalharAlimentoDTO(alimento);
    }
}
