package com.db.desafio_votacao_v1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SessaoRecordRequest(
        @NotNull(message = "O campo ID da Pauta é obrigatório!")
        Long idPauta,
        @Positive(message = "A duração da Sessão deve ser um número positivo!")
        Integer duracaoMinutos) {
}
