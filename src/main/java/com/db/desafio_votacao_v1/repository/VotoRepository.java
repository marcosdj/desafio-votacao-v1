package com.db.desafio_votacao_v1.repository;

import com.db.desafio_votacao_v1.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {
    @Query("SELECT COUNT(v) > 0 FROM Voto v WHERE v.associado.id = :associadoId AND v.pauta.id = :pautaId")
    boolean associadoJaVotou(@Param("associadoId") Long associadoId, @Param("pautaId") Long pautaId);

    @Query("SELECT v.voto, COUNT(v) FROM Voto v WHERE v.pauta.id = :pautaId GROUP BY v.voto")
    List<Object[]> contabilizarVotos(@Param("pautaId") Long pautaId);
}
