package com.db.desafio_votacao_v1.service;

import com.db.desafio_votacao_v1.domain.Associado;
import com.db.desafio_votacao_v1.dto.AssociadoRecordResponse;
import com.db.desafio_votacao_v1.exception.EntidadeNaoEncontradaException;
import com.db.desafio_votacao_v1.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AssociadoService {
    private final AssociadoRepository associadoRepository;

    @Transactional
    public AssociadoRecordResponse cadastrarAssociado(String nome) {
        var associado = new Associado(nome);
        associadoRepository.save(associado);

        return new AssociadoRecordResponse(associado.getId(), associado.getNome());
    }

    @Transactional(readOnly = true)
    public AssociadoRecordResponse buscarAssociado(Long idAssociado) {
        var associado = associadoRepository.findById(idAssociado)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Associado com o ID %d não encontrado!",
                        idAssociado
                ));

        return new AssociadoRecordResponse(associado.getId(), associado.getNome());
    }
}
