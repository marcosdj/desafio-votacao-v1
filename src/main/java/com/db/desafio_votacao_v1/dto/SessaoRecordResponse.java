package com.db.desafio_votacao_v1.dto;

import java.time.LocalDateTime;

public record SessaoRecordResponse(
        Long id,
        PautaRecordResponse pauta,
        LocalDateTime dataInicio) {
}
