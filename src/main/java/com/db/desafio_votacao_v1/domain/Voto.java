package com.db.desafio_votacao_v1.domain;

import com.db.desafio_votacao_v1.enums.VotoEnum;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_voto")
public class Voto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVoto")
    private Long id;

    @OneToOne
    @JoinColumn(name = "associado_id")
    @Column(name = "associado")
    private Associado associado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pauta_id", nullable = false)
    @Column(name = "pauta")
    private Pauta pauta;

    @Enumerated(EnumType.STRING)
    @Column(name = "voto", nullable = false)
    private VotoEnum voto;

    public Voto() {}

    public Voto(Associado associado, String voto, Pauta pauta) {
        this.associado = associado;
        this.voto = VotoEnum.toEnum(voto);
        this.pauta = pauta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public VotoEnum getVoto() {
        return voto;
    }

    public void setVoto(VotoEnum voto) {
        this.voto = voto;
    }
}
