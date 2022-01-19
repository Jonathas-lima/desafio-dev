package br.com.bycoderstec.desafiodev.service.impl;

import br.com.bycoderstec.desafiodev.model.Loja;
import br.com.bycoderstec.desafiodev.model.Representante;
import br.com.bycoderstec.desafiodev.model.Transacao;
import br.com.bycoderstec.desafiodev.model.enums.TipoDadoEnum;
import br.com.bycoderstec.desafiodev.model.enums.TipoTransacaoEnum;
import br.com.bycoderstec.desafiodev.service.FileService;
import br.com.bycoderstec.desafiodev.service.LojaService;
import br.com.bycoderstec.desafiodev.service.ProcessamentoService;
import br.com.bycoderstec.desafiodev.service.TransacaoService;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.bycoderstec.desafiodev.model.enums.TipoDadoEnum.*;
import static java.lang.Double.parseDouble;

@Service
public class ProcessamentoServiceImpl implements ProcessamentoService {

    private final FileService fileService;
    private final TransacaoService transacaoService;
    private final LojaService lojaService;

    public ProcessamentoServiceImpl(FileService fileService, TransacaoService transacaoService, LojaService lojaService) {
        this.fileService = fileService;
        this.transacaoService = transacaoService;
        this.lojaService = lojaService;
    }

    @Override
    public List<Transacao> processarDados(InputStream file) {

        Optional<List<String>> stringTransacoes = fileService.readLineFile(file);
        if(stringTransacoes.isPresent()){
            List<String> transacoesList = stringTransacoes.get();
            List<Transacao>transacoes = transacoesList.stream().map(this::stringToTransacao).collect(Collectors.toList());
            return transacaoService.salvarTransacoes(transacoes);
        }
       return Collections.emptyList();
    }

    private Transacao stringToTransacao(String transacao) {
        return Transacao.builder()
                .tipoTransacao(obterTipoTransacao(transacao))
                .dataOcorrencia(obterDataOcorrencia(transacao))
                .horaOcorrencia(obterHoraOcorrencia(transacao))
                .valor(obterValor(transacao))
                .cartaoUtilizado(obterNumeroCartao(transacao))
                .loja(obterDadosLoja(transacao))
                .build();
    }

    private TipoTransacaoEnum obterTipoTransacao(String linha) {
        return TipoTransacaoEnum.getByCod(getData(linha, TIPO_TRANSACAO));
    }

    private String obterDataOcorrencia(String transacao) {
        return getData(transacao, DATA_OCORRENCIA);
    }

    private String obterHoraOcorrencia(String transacao) {
        return getData(transacao, HORA_OCORRENCIA);
    }

    private BigDecimal obterValor(String transacao) {
        BigDecimal valor = BigDecimal.valueOf(parseDouble(getData(transacao, VALOR)));
        return valor.divide(BigDecimal.valueOf(100));
    }

    private String obterNumeroCartao(String transacao) {
        return getData(transacao, NUM_CARTAO);
    }

    private Loja obterDadosLoja(String transacao) {
        Loja loja = Loja.builder()
                .nomeLoja(getData(transacao, NOME_LOJA).trim())
                .representante(obterRepresentanteLoja(transacao))
                .build();
        return lojaService.salvarLoja(loja);
    }

    private Representante obterRepresentanteLoja(String transacao){
        return Representante.builder()
                .nome(getData(transacao, DONO_DA_LOJA).trim())
                .cpf(getData(transacao, CPF))
                .build();
    }

    private String getData(String linha, TipoDadoEnum tipoDado) {
        int beginIndex = tipoDado.getInicio() - 1;
        int endIndex = beginIndex + tipoDado.getTamanho();
        return linha.substring(beginIndex, endIndex);
    }
}
