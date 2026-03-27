package com.db.desafio_votacao_v1.service;

import com.db.desafio_votacao_v1.domain.Pauta;
import com.db.desafio_votacao_v1.domain.Sessao;
import com.db.desafio_votacao_v1.dto.SessaoRecordRequest;
import com.db.desafio_votacao_v1.dto.SessaoRecordResponse;
import com.db.desafio_votacao_v1.exception.EntidadeNaoEncontradaException;
import com.db.desafio_votacao_v1.repository.PautaRepository;
import com.db.desafio_votacao_v1.repository.SessaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SessaoServiceTest {
    @Mock
    private SessaoRepository sessaoRepository;
    @Mock
    private PautaRepository pautaRepository;
    @InjectMocks
    private SessaoService sessaoService;

    @Test
    void abrirSessaoComSucesso() {
        Long idPauta = 1L;
        SessaoRecordRequest request = new SessaoRecordRequest(idPauta, null);

        Pauta pauta = new Pauta();
        pauta.setId(idPauta);
        pauta.setConteudo("Pauta Teste");

        when(pautaRepository.findById(idPauta)).thenReturn(Optional.of(pauta));

        when(sessaoRepository.save(any(Sessao.class))).thenAnswer(invocation -> {
            Sessao s = invocation.getArgument(0);
            s.setId(100L);
            return s;
        });

        SessaoRecordResponse response = sessaoService.abrirSessao(request);

        assertNotNull(response);
        assertEquals(100L, response.id());
        assertEquals("Pauta Teste", response.pauta().conteudo());
        assertNotNull(response.dataInicio());

        verify(pautaRepository).findById(idPauta);
        verify(sessaoRepository).save(any(Sessao.class));
    }

    @Test
    void abrirSessaoExcecaoPautaNaoEncontrada() {
        SessaoRecordRequest request = new SessaoRecordRequest(99L, null);
        when(pautaRepository.findById(99L)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(EntidadeNaoEncontradaException.class, () -> {
            sessaoService.abrirSessao(request);
        });

        assertEquals("Pauta com o ID 99 não encontrada!", exception.getMessage());
        verify(sessaoRepository, never()).save(any());
    }

    @Test
    void buscarSessaoComSucesso() {
        Long idSessao = 100L;
        Pauta pauta = new Pauta();
        pauta.setConteudo("Conteúdo Pauta");

        Sessao sessao = new Sessao();
        sessao.setId(idSessao);
        sessao.setPauta(pauta);
        sessao.setDataFim(LocalDateTime.now());

        when(sessaoRepository.findById(idSessao)).thenReturn(Optional.of(sessao));

        SessaoRecordResponse response = sessaoService.buscarSessao(idSessao);

        assertNotNull(response);
        assertEquals(idSessao, response.id());
        assertEquals("Conteúdo Pauta", response.pauta().conteudo());
        verify(sessaoRepository).findById(idSessao);
    }

    @Test
    void buscarSessaoExcecaoNaoEncontrada() {
        when(sessaoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntidadeNaoEncontradaException.class, () -> sessaoService.buscarSessao(1L));
    }
}
