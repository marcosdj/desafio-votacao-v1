package com.db.desafio_votacao_v1.service;

import com.db.desafio_votacao_v1.domain.Voto;
import com.db.desafio_votacao_v1.dto.VotoRecordRequest;
import com.db.desafio_votacao_v1.repository.AssociadoRepository;
import com.db.desafio_votacao_v1.repository.PautaRepository;
import com.db.desafio_votacao_v1.repository.SessaoRepository;
import com.db.desafio_votacao_v1.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VotoService {
    private final VotoRepository votoRepository;
    private final AssociadoRepository associadoRepository;
    private final PautaRepository pautaRepository;
    private final SessaoRepository sessaoRepository;

    @Transactional
    public void votar(VotoRecordRequest votoRecordRequest) {
        var associado = associadoRepository.findById(votoRecordRequest.associadoId())
                .orElseThrow(() -> new RuntimeException("Associado com o ID %d não encontrado!"));

        var sessao = sessaoRepository.findById(votoRecordRequest.sessaoId())
                .orElseThrow(() -> new RuntimeException("Pauta com o ID  %d não encontrada!"));

        var pauta = pautaRepository.findById(sessao.getPauta().getId())
                .orElseThrow(() -> new RuntimeException("Pauta com o ID  %d não encontrada!"));

        if (!sessao.estaAberta()) {
            throw new RuntimeException("A sessão de votação já está encerrada!");
        }

        if(votoRepository.associadoJaVotou(votoRecordRequest.associadoId(), pauta.getId())) {
            throw new RuntimeException("Este associado já votou nessa pauta!");
        }

        Voto voto = new Voto(associado, votoRecordRequest.votoDoAssociado(), pauta);
        votoRepository.save(voto);
    }
}