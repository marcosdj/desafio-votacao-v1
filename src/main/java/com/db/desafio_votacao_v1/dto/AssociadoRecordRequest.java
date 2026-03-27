package com.db.desafio_votacao_v1.dto;

import jakarta.validation.constraints.NotBlank;

public record AssociadoRecordRequest(
        @NotBlank(message = "O campo Nome do Associado é obrigatório!")
        String nome) {
}
