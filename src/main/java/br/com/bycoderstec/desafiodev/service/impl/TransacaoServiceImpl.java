package br.com.bycoderstec.desafiodev.service.impl;

import br.com.bycoderstec.desafiodev.model.Transacao;
import br.com.bycoderstec.desafiodev.repository.TransacaoRepository;
import br.com.bycoderstec.desafiodev.service.TransacaoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoServiceImpl(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @Override
    public List<Transacao> salvarTransacoes(List<Transacao> transacaoList) {
        return transacaoRepository.saveAll(transacaoList);
    }
}
