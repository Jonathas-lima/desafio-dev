package br.com.bycoderstec.desafiodev.web.rest;

import br.com.bycoderstec.desafiodev.model.Loja;
import br.com.bycoderstec.desafiodev.model.Transacao;
import br.com.bycoderstec.desafiodev.model.enums.TipoTransacaoEnum;
import br.com.bycoderstec.desafiodev.service.LojaService;
import br.com.bycoderstec.desafiodev.web.rest.response.LojaResponse;
import br.com.bycoderstec.desafiodev.web.rest.response.TransacaoResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static br.com.bycoderstec.desafiodev.model.enums.TipoTransacaoEnum.CREDITO;
import static br.com.bycoderstec.desafiodev.util.JsonUtilsTest.convertToListObject;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LojaController.class)
@AutoConfigureMockMvc
public class LojaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LojaService lojaService;


    @Test
    public void deveriaRetornarListaDasLojas() throws Exception {

        when(lojaService.obterLojas()).thenReturn(obterLojasPreenchidos());

        MvcResult mvcResult = this.mockMvc.perform(get("/api/lojas")
                        .characterEncoding("utf-8"))
                        .andExpect(status().isOk())
                        .andReturn();
        String respostaJson = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);

        List<LojaResponse> lojaResponses = convertToListObject(respostaJson, new TypeReference<>() {});

        assertNotNull(lojaResponses);
        assertFalse(lojaResponses.isEmpty());
        assertEquals(1, lojaResponses.size());
        assertIterableEquals(obterLojaResponseEsperado(), lojaResponses);
    }


    private List<Loja> obterLojasPreenchidos() {
        Loja loja1 = Loja.builder()
                .nomeLoja("LOJA 1")
                .transacoes(obterTransacoesPreenchidas())
                .build();

        return List.of(loja1);
    }

    private List<Transacao> obterTransacoesPreenchidas() {

        Transacao transacao1 = Transacao.builder()
                .valor(BigDecimal.TEN)
                .dataOcorrencia("20220120")
                .tipoTransacao(CREDITO)
                .build();

        Transacao transacao2 = Transacao.builder()
                .valor(BigDecimal.ONE)
                .dataOcorrencia("20220120")
                .tipoTransacao(CREDITO)
                .build();
        return asList(transacao1, transacao2);
    }

    private List<LojaResponse> obterLojaResponseEsperado() {
        LojaResponse lojaResponse = LojaResponse.builder()
                .nomeLoja("LOJA 1")
                .saldo(BigDecimal.valueOf(11L))
                .transacoes(obterTransacoesResponseEsperado())
                .build();
        return List.of(lojaResponse);
    }

    private List<TransacaoResponse> obterTransacoesResponseEsperado() {
        TransacaoResponse transacaoResponse1 = TransacaoResponse.builder()
                .desc(CREDITO.getDescricao())
                .data("20220120")
                .valor(BigDecimal.TEN)
                .sinal(CREDITO.getSinal())
                .build();
        TransacaoResponse transacaoResponse2 = TransacaoResponse.builder()
                .desc(CREDITO.getDescricao())
                .data("20220120")
                .valor(BigDecimal.ONE)
                .sinal(CREDITO.getSinal())
                .build();
        return asList(transacaoResponse1, transacaoResponse2);
    }
}
