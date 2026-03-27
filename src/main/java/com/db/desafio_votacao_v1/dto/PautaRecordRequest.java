package com.db.desafio_votacao_v1.dto;

import jakarta.validation.constraints.NotBlank;

public record PautaRecordRequest(
        @NotBlank(message = "O campo Conteúdo da pauta é obrigatório!")
        String conteudo) {
}
