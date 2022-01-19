package br.com.bycoderstec.desafiodev.service;

import br.com.bycoderstec.desafiodev.service.impl.FileServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FileServiceImplTest {


    private FileService fileService;

    @BeforeEach
    public void beforeEach() {
        this.fileService = new FileServiceImpl();
    }

    @Test
    public void deveriaRetornarConteudo() {

        Optional<List<String>> conteudo = fileService.readLineFile(getFileInputStream());
        assertTrue(conteudo.isPresent());

        List<String> strings = conteudo.get();
        assertEquals("linha1", strings.get(0));
        assertEquals("linha2", strings.get(1));
    }

    private InputStream getFileInputStream() {
        String initialString = "linha1\nlinha2";
        return new ByteArrayInputStream(initialString.getBytes());
    }
}
