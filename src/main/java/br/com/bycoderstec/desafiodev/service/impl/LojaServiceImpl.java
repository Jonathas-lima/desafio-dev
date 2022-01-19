package br.com.bycoderstec.desafiodev.service.impl;

import br.com.bycoderstec.desafiodev.model.Loja;
import br.com.bycoderstec.desafiodev.repository.LojaRepository;
import br.com.bycoderstec.desafiodev.service.LojaService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LojaServiceImpl implements LojaService {

    private final LojaRepository lojaRepository;

    public LojaServiceImpl(LojaRepository lojaRepository) {
        this.lojaRepository = lojaRepository;
    }

    @Override
    public Loja salvarLoja(Loja loja) {

        Optional<Loja> lojaSalva = lojaRepository.findByNomeLoja(loja.getNomeLoja());
        return lojaSalva.orElseGet(() -> lojaRepository.save(loja));
    }
}
