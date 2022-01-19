package br.com.bycoderstec.desafiodev.service;

import br.com.bycoderstec.desafiodev.model.Transacao;

import java.util.List;

public interface TransacaoService {

    List<Transacao> salvarTransacoes(List<Transacao> transacaoList);
}
