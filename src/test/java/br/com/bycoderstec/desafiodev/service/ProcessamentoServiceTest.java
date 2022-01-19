package br.com.bycoderstec.desafiodev.service;

import br.com.bycoderstec.desafiodev.model.Loja;
import br.com.bycoderstec.desafiodev.model.Representante;
import br.com.bycoderstec.desafiodev.model.Transacao;
import br.com.bycoderstec.desafiodev.model.enums.TipoTransacaoEnum;
import br.com.bycoderstec.desafiodev.repository.LojaRepository;
import br.com.bycoderstec.desafiodev.repository.TransacaoRepository;
import br.com.bycoderstec.desafiodev.service.impl.FileServiceImpl;
import br.com.bycoderstec.desafiodev.service.impl.LojaServiceImpl;
import br.com.bycoderstec.desafiodev.service.impl.ProcessamentoServiceImpl;
import br.com.bycoderstec.desafiodev.service.impl.TransacaoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static br.com.bycoderstec.desafiodev.model.enums.TipoTransacaoEnum.FINANCIAMENTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProcessamentoServiceTest {

    private FileService fileService;
    private TransacaoService transacaoService;
    private LojaService lojaService;
    private ProcessamentoService processamentoService;

    private LojaRepository lojaRepository;
    private TransacaoRepository transacaoRepository;

    @BeforeEach
    public void beforeEach() {
        transacaoRepository = mock(TransacaoRepository.class);
        transacaoService = new TransacaoServiceImpl(transacaoRepository);

        lojaRepository = mock(LojaRepository.class);
        lojaService = new LojaServiceImpl(lojaRepository);

        fileService = new FileServiceImpl();

        processamentoService = new ProcessamentoServiceImpl(fileService, transacaoService,lojaService);
    }

    @Test
    public void deveriaDecodificarDados() {
        when(lojaRepository.save(any())).thenReturn(getLojaPreenchido());
        when(transacaoRepository.saveAll(any())).thenReturn(getTransacoes());

        List<Transacao> transacoes = processamentoService.processarDados(getFileInputStream());

        assertFalse(transacoes.isEmpty());
        assertEquals(1, transacoes.size());

        Transacao transacao = transacoes.get(0);

        assertEquals(FINANCIAMENTO, transacao.getTipoTransacao());
        assertEquals("20190301", transacao.getDataOcorrencia());
        assertEquals("172712", transacao.getHoraOcorrencia());
        assertEquals("6777****1313", transacao.getCartaoUtilizado());
        assertEquals(BigDecimal.valueOf(192.0), transacao.getValor());
        assertNotNull(transacao.getLoja());
    }

    @Test
    public void deveriaRetornarListaVaziaSeNaoLerArquivo() {
        fileService = mock(FileService.class);
        processamentoService = new ProcessamentoServiceImpl(fileService, transacaoService,lojaService);

        when(fileService.readLineFile(any())).thenReturn(Optional.empty());

        List<Transacao> transacoes = processamentoService.processarDados(getFileInputStream());

        verify(fileService).readLineFile(any());
        assertTrue(transacoes.isEmpty());
    }

    private Loja getLojaPreenchido(){
        return Loja.builder()
                .id(1L)
                .nomeLoja("PEREIRAMERCADO DA AVENIDA")
                .representante(Representante.builder().build())
                .build();
    }

    private List<Transacao> getTransacoes() {
        Transacao transacao = Transacao.builder()
                .id(1)
                .tipoTransacao(FINANCIAMENTO)
                .dataOcorrencia("20190301")
                .horaOcorrencia("172712")
                .valor(BigDecimal.valueOf(192.0))
                .cartaoUtilizado("6777****1313")
                .loja(getLojaPreenchido())
                .build();

        return List.of(transacao);
    }

    private InputStream getFileInputStream() {
        String initialString = "3201903010000019200845152540736777****1313172712MARCOS PEREIRAMERCADO DA AVENIDA";
        return new ByteArrayInputStream(initialString.getBytes());
    }
}
