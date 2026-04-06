package com.db.desafio_votacao_v1.enums;

import java.util.Objects;

public enum VotoEnum {
    S("Sim"),
    N("Não");

    private String descricao;

    VotoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static VotoEnum toEnum(String descricao) {
        if (Objects.isNull(descricao))
            return null;

        for (VotoEnum votoEnum : VotoEnum.values()) {
            if (descricao.equals(votoEnum.getDescricao()))
                return votoEnum;
        }

        throw new IllegalArgumentException("Invalid code: " + descricao);
    }
}