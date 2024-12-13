package com.dtplan.domain.dieta;

import com.dtplan.domain.alimento.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DietaRepository extends JpaRepository<Dieta, Long> {

    //@Query("SELECT DISTINCT d FROM Dieta d JOIN d.refeicoes r JOIN r.alimentos a WHERE a = :alimento")
    //List<Dieta> findAllByAlimento(Alimento alimento);
}
