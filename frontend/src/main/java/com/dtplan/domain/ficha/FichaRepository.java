package com.dtplan.domain.ficha;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FichaRepository extends JpaRepository<Ficha, Long> {
    List<Ficha> findByTreinoId(Long id);
    //Page<Ficha> findByTreinoId(Long id, Pageable pageable);
}
