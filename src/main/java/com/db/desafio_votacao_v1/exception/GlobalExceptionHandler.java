package com.db.desafio_votacao_v1.exception;

import com.db.desafio_votacao_v1.dto.exception.MensagemErroRecord;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<MensagemErroRecord> handleNegocioException(NegocioException ex) {
        var erro = new MensagemErroRecord(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<MensagemErroRecord> handleNotFound(EntidadeNaoEncontradaException ex) {
        var erro = new MensagemErroRecord(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MensagemErroRecord> handleNotFound(EntityNotFoundException ex) {
        var erro = new MensagemErroRecord(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensagemErroRecord> handleGenericException(Exception ex) {
        ex.printStackTrace();

        var erro = new MensagemErroRecord(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro interno inesperado no servidor.",
                null,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensagemErroRecord> handleValidation(MethodArgumentNotValidException ex) {
        List<String> detalhes = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        var erro = new MensagemErroRecord(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação nos campos",
                detalhes,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
    }
}
