package com.db.desafio_votacao_v1.repository;

import com.db.desafio_votacao_v1.domain.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {
}
