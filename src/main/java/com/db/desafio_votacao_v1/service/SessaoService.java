package com.db.desafio_votacao_v1.service;

import com.db.desafio_votacao_v1.domain.Sessao;
import com.db.desafio_votacao_v1.dto.PautaRecordResponse;
import com.db.desafio_votacao_v1.dto.SessaoRecordRequest;
import com.db.desafio_votacao_v1.dto.SessaoRecordResponse;
import com.db.desafio_votacao_v1.repository.PautaRepository;
import com.db.desafio_votacao_v1.repository.SessaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SessaoService {
    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;

    @Transactional
    public SessaoRecordResponse abrirSessao(SessaoRecordRequest sessaoRecordRequest) {
        var pauta = pautaRepository.findById(sessaoRecordRequest.idPauta())
                .orElseThrow(() -> new RuntimeException("Pauta com o ID %d não encontrada!"));

        int minutos = (sessaoRecordRequest.duracaoMinutos() != null && sessaoRecordRequest.duracaoMinutos() > 0)
                ? sessaoRecordRequest.duracaoMinutos() : 1;

        var sessao = new Sessao();
        sessao.setPauta(pauta);
        sessao.setDataFim(LocalDateTime.now().plusMinutes(minutos));

        var pautaResponse = new PautaRecordResponse(sessao.getId(), sessao.getPauta().getConteudo());

        sessaoRepository.save(sessao);

        return new SessaoRecordResponse(sessao.getId(), pautaResponse, sessao.getDataFim());
    }

    @Transactional(readOnly=true)
    public SessaoRecordResponse buscarSessao(Long idSessao) {
        var sessao = sessaoRepository.findById(idSessao)
                .orElseThrow(() -> new RuntimeException("Sessão com o ID %d não encontrado!"));

        var pautaResponse = new PautaRecordResponse(sessao.getId(), sessao.getPauta().getConteudo());

        return new SessaoRecordResponse(sessao.getId(), pautaResponse, sessao.getDataFim());
    }
}
