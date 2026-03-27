package com.db.desafio_votacao_v1.repository;

import com.db.desafio_votacao_v1.domain.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {
    @Query("SELECT s FROM Sessao s WHERE s.pauta.id = :pautaId")
    Optional<Sessao> findByPautaId(@Param("pautaId") Long pautaId);
}
