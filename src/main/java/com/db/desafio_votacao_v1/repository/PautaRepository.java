package com.db.desafio_votacao_v1.repository;

import com.db.desafio_votacao_v1.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
}
