package com.db.desafio_votacao_v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VotoRecordRequest(
        @NotNull(message = "O campo ID do Associado é obrigatório!")
        Long associadoId,
        @NotBlank(message = "O campo Voto do Associado é obrigatório!")
        String votoDoAssociado,
        @NotNull(message = "O campo ID da Sessão é obrigatório!")
        Long sessaoId) {
}
