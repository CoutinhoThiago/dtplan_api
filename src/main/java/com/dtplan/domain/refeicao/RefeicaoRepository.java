package com.dtplan.domain.refeicao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefeicaoRepository extends JpaRepository<Refeicao, Long> {
    List<Refeicao> findByIdIn(List<Long> ids);
}
