package com.db.desafio_votacao_v1.dto.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record MensagemErroRecord(
        int status,
        String mensagem,
        List<String> erros,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime data) {
}
