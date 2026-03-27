package com.db.desafio_votacao_v1.dto;

public record ResultadoVotacaoRecordResponse(
        String conteudo,
        Long sim,
        Long nao,
        String status) {
}
