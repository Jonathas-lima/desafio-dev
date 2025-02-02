package br.com.bycoderstec.desafiodev.service;

import br.com.bycoderstec.desafiodev.model.Loja;
import br.com.bycoderstec.desafiodev.model.Representante;
import br.com.bycoderstec.desafiodev.repository.LojaRepository;
import br.com.bycoderstec.desafiodev.service.impl.LojaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LojaServiceTest {

    private LojaService lojaService;

    private LojaRepository lojaRepository;

    @BeforeEach
    public void beforeEach() {
        lojaRepository = mock(LojaRepository.class);
        lojaService = new LojaServiceImpl(lojaRepository);
    }

    @Test
    public void deveriaBuscarLojaExistente(){
        String NOME_LOJA = "Nome da loja";
        when(lojaRepository.findByNomeLoja(anyString())).thenReturn(getLojaPreenchido());

        Loja lojaSalva = lojaService.salvarLoja(Loja.builder().nomeLoja(NOME_LOJA).build());

        assertNotNull(lojaSalva);
        assertEquals(NOME_LOJA, lojaSalva.getNomeLoja());
        assertNotNull(lojaSalva.getRepresentante());
    }

    @Test
    public void deveriaSalvarLojaNaoExistente() {
        String NOME_LOJA = "Nome da loja";
        when(lojaRepository.save(any())).thenReturn(getLojaPreenchido().get());

        Loja lojaSalva = lojaService.salvarLoja(Loja.builder().nomeLoja(NOME_LOJA).build());

        assertNotNull(lojaSalva);
        assertEquals(NOME_LOJA, lojaSalva.getNomeLoja());
        assertNotNull(lojaSalva.getRepresentante());
    }

    @Test
    public void deveriaBuscarTodasAsLojas() {
        when(lojaRepository.findAll()).thenReturn(listaLojas());

        List<Loja> lojas = lojaService.obterLojas();
        assertFalse(lojas.isEmpty());
        assertEquals(2, lojas.size());
    }


    private Optional<Loja> getLojaPreenchido(){
        return Optional.of(Loja.builder()
                .nomeLoja("Nome da loja")
                .representante(Representante.builder().build())
                .build());
    }

    private List<Loja> listaLojas() {

        Loja loja1 = Loja.builder().build();
        Loja loja2 = Loja.builder().build();

        return asList(loja1, loja2);
    }
}
