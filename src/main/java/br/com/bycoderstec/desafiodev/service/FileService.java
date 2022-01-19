package br.com.bycoderstec.desafiodev.service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public interface FileService {

    Optional<List<String>> readLineFile(InputStream file);
}
