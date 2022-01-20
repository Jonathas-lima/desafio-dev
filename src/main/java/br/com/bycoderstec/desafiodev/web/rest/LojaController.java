package br.com.bycoderstec.desafiodev.web.rest;

import br.com.bycoderstec.desafiodev.service.LojaService;
import br.com.bycoderstec.desafiodev.web.rest.response.LojaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LojaController {

    private final LojaService lojaService;

    public LojaController(LojaService lojaService) {
        this.lojaService = lojaService;
    }

    @GetMapping("/lojas")
    public ResponseEntity<List<LojaResponse>> obterLojasTransacoes() {
        List<LojaResponse> lojas = lojaService.obterLojas()
                .stream()
                .map(LojaResponse::of)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lojas);
    }
}
