package br.com.bycoderstec.desafiodev.service;

import br.com.bycoderstec.desafiodev.model.Transacao;
import br.com.bycoderstec.desafiodev.repository.TransacaoRepository;
import br.com.bycoderstec.desafiodev.service.impl.TransacaoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransacaoServiceTest {

    private TransacaoRepository transacaoRepository;

    private TransacaoService transacaoService;

    @BeforeEach
    public void beforeEach() {
        transacaoRepository = mock(TransacaoRepository.class);
        this.transacaoService = new TransacaoServiceImpl(transacaoRepository);
    }

    @Test
    public void salvarListaDeTransacoes() {
        when(transacaoRepository.saveAll(any())).thenReturn(getTransacoes());

        List<Transacao> transacoes = transacaoService.salvarTransacoes(getTransacoes());

        assertFalse(transacoes.isEmpty());
        assertEquals(1, transacoes.size());
    }


    private List<Transacao> getTransacoes() {
        return List.of(Transacao.builder().build());
    }
}
