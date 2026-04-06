package com.db.desafio_votacao_v1.service;

import com.db.desafio_votacao_v1.domain.Pauta;
import com.db.desafio_votacao_v1.dto.PautaRecordRequest;
import com.db.desafio_votacao_v1.dto.PautaRecordResponse;
import com.db.desafio_votacao_v1.dto.ResultadoVotacaoRecordResponse;
import com.db.desafio_votacao_v1.enums.VotoEnum;
import com.db.desafio_votacao_v1.exception.EntidadeNaoEncontradaException;
import com.db.desafio_votacao_v1.repository.PautaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class PautaService {
    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Transactional(readOnly = true)
    public Pauta buscarPauta(Long idPauta) {
        return pautaRepository.findById(idPauta)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Pauta com o ID %d não encontrada!", idPauta));
    }

    public PautaRecordResponse buscarPautaResponse(Long idPauta) {
        var pauta = this.buscarPauta(idPauta);
        return new PautaRecordResponse(pauta.getId(), pauta.getConteudo());
    }

    @Transactional
    public PautaRecordResponse cadastrarPauta(PautaRecordRequest pautaRecordRequest) {
        var pauta = new Pauta();
        pauta.setVotos(new ArrayList<>());
        BeanUtils.copyProperties(pautaRecordRequest, pauta);

        var pautaSalva = pautaRepository.save(pauta);

        return new PautaRecordResponse(pautaSalva.getId(), pautaSalva.getConteudo());
    }

    @Transactional(readOnly = true)
    public ResultadoVotacaoRecordResponse obterResultado(Long idPauta) {
        var pauta = this.buscarPauta(idPauta);
        var votos = pauta.getVotos();

        long totalVotosSim = votos.stream().filter(voto -> voto.getVoto() == VotoEnum.S).count();
        long totalVotosNao = votos.stream().filter(voto -> voto.getVoto() == VotoEnum.N).count();

        String status = totalVotosSim > totalVotosNao ? "APROVADA" : (totalVotosNao > totalVotosSim ? "REPROVADA" : "EMPATE");

        return new ResultadoVotacaoRecordResponse(
                pauta.getConteudo(), totalVotosSim, totalVotosNao, status);
    }
}