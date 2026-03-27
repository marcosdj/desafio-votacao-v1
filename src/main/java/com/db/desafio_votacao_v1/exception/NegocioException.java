package com.db.desafio_votacao_v1.exception;

public class NegocioException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NegocioException(String mensagem) {
        super(mensagem);
    }

    public NegocioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public NegocioException(String formato, Object... argumentos) {
        super(formato.formatted(argumentos));
    }
}
