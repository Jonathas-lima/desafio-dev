package br.com.bycoderstec.desafiodev.service;

import br.com.bycoderstec.desafiodev.model.Transacao;

import java.io.InputStream;
import java.util.List;

public interface ProcessamentoService {

    List<Transacao> processarDados(InputStream file);
}
