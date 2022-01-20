package br.com.bycoderstec.desafiodev.service;

import br.com.bycoderstec.desafiodev.model.Loja;

import java.util.List;

public interface LojaService {

    Loja salvarLoja(Loja loja);

    List<Loja> obterLojas();
}
