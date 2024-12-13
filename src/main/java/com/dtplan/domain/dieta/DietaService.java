package com.dtplan.domain.dieta;

import com.dtplan.domain.alimento.Alimento;
import com.dtplan.domain.dieta.dto.CadastrarDietaDTO;
import com.dtplan.domain.dieta.dto.EditarDietaDTO;
import com.dtplan.domain.refeicao.Refeicao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DietaService {

    @Autowired
    private DietaRepository dietaRepository;

    @Transactional
    public CadastrarDietaDTO cadastrarDieta(CadastrarDietaDTO dados) {
        Dieta dieta = new Dieta(dados);

        dietaRepository.save(dieta);

        return new CadastrarDietaDTO(dieta.getDescricao(), dieta.getAutor(), dieta.getTipo(), dieta.getUsuario());
    }

    @Transactional
    public EditarDietaDTO editarDieta(long id, EditarDietaDTO dados) {

        Optional<Dieta> dietaOpt = dietaRepository.findById(id);
        Dieta dieta = dietaOpt.get();

        dieta.atualizaInformacoes(dados);

        dietaRepository.save(dieta);

        return new EditarDietaDTO(dieta.getDescricao(), dieta.getAutor(), dieta.getTipo(), dieta.getUsuario(), dieta.getCalorias(), dieta.getProteina(), dieta.getGordura(), dieta.getCarboidrato(), dieta.getFibraAlimentar());
    }
}

