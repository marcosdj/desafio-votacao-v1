package com.db.desafio_votacao_v1.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_sessao")
public class Sessao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSessao")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_id", referencedColumnName = "id", nullable = false)
    @Column(name = "pauta")
    private Pauta pauta;

    @Column(name = "dataFim", nullable = false)
    private LocalDateTime dataFim;

    public Sessao() {}

    public Sessao(Pauta pauta) {
        this.pauta = pauta;
    }

    public boolean estaAberta() {
        return LocalDateTime.now().isBefore(dataFim);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }
}
