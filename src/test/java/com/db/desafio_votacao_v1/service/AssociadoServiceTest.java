package com.db.desafio_votacao_v1.service;

import com.db.desafio_votacao_v1.domain.Associado;
import com.db.desafio_votacao_v1.exception.EntidadeNaoEncontradaException;
import com.db.desafio_votacao_v1.repository.AssociadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AssociadoServiceTest {
    @Mock
    private AssociadoRepository repository;

    @InjectMocks
    private AssociadoService service;

    @Test
    void deveCadastrarAssociadoComSucesso() {
        Associado associado = new Associado("João Silva");
        associado.setId(1L);
        when(repository.save(any(Associado.class))).thenReturn(associado);

        var response = service.cadastrarAssociado("João Silva");

        assertNotNull(response.nome());
        assertEquals("João Silva", response.nome());
        verify(repository, times(1)).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoAssociadoNaoEncontrado() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> service.buscarAssociado(1L));
    }
}
