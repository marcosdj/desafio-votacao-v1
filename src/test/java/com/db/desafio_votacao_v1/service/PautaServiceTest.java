package com.db.desafio_votacao_v1.service;

import com.db.desafio_votacao_v1.domain.Pauta;
import com.db.desafio_votacao_v1.domain.Voto;
import com.db.desafio_votacao_v1.enums.VotoEnum;
import com.db.desafio_votacao_v1.repository.PautaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PautaServiceTest {
    @Mock
    private PautaRepository repository;
    @InjectMocks
    private PautaService service;

    @Test
    void deveCalcularResultadoComVitoriaDoSim() {
        Pauta pauta = new Pauta();
        pauta.setConteudo("Pauta Teste");
        pauta.setVotos(List.of(
                new Voto(null, VotoEnum.S.getDescricao(), pauta),
                new Voto(null, VotoEnum.S.getDescricao(), pauta),
                new Voto(null, VotoEnum.N.getDescricao(), pauta)
        ));

        when(repository.findById(1L)).thenReturn(Optional.of(pauta));

        var resultado = service.obterResultado(1L);

        assertEquals(2, resultado.sim());
        assertEquals(1, resultado.nao());
        assertEquals("APROVADA", resultado.status());
    }
}
