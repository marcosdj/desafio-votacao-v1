package com.db.desafio_votacao_v1.domain;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_associado")
public class Associado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAssociado")
    private Long id;

    @Column(name = "nome")
    private String nome;

    public Associado() {}

    public Associado(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
