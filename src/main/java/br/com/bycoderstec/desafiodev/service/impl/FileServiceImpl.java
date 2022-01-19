package br.com.bycoderstec.desafiodev.service.impl;

import br.com.bycoderstec.desafiodev.service.FileService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public Optional<List<String>> readLineFile(InputStream file) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
            return Optional.of(reader.lines().collect(Collectors.toList()));

        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
