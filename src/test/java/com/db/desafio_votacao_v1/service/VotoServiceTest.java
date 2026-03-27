package com.db.desafio_votacao_v1.service;

import com.db.desafio_votacao_v1.domain.Associado;
import com.db.desafio_votacao_v1.domain.Pauta;
import com.db.desafio_votacao_v1.domain.Sessao;
import com.db.desafio_votacao_v1.dto.VotoRecordRequest;
import com.db.desafio_votacao_v1.enums.VotoEnum;
import com.db.desafio_votacao_v1.exception.NegocioException;
import com.db.desafio_votacao_v1.repository.AssociadoRepository;
import com.db.desafio_votacao_v1.repository.PautaRepository;
import com.db.desafio_votacao_v1.repository.SessaoRepository;
import com.db.desafio_votacao_v1.repository.VotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VotoServiceTest {
    @Mock
    private VotoRepository votoRepository;
    @Mock
    private SessaoRepository sessaoRepository;
    @Mock
    private AssociadoRepository associadoRepository;
    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private VotoService service;

    @Test
    void deveLancarExcecaoSeAssociadoJaVotou() {
        var request = new VotoRecordRequest(1L, VotoEnum.S.getDescricao(), 10L);

        var pauta = new Pauta();
        pauta.setId(100L);

        var sessao = new Sessao();
        sessao.setId(10L);
        sessao.setDataFim(LocalDateTime.now());
        sessao.setPauta(pauta);

        var associado = new Associado();
        associado.setId(1L);

        when(associadoRepository.findById(1L)).thenReturn(Optional.of(associado));
        when(pautaRepository.findById(100L)).thenReturn(Optional.of(pauta));
        when(sessaoRepository.findById(10L)).thenReturn(Optional.of(sessao));

        assertThrows(NegocioException.class, () -> service.votar(request));
        verify(votoRepository, never()).save(any());
    }
}
