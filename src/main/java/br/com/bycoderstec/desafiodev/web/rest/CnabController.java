package br.com.bycoderstec.desafiodev.web.rest;

import br.com.bycoderstec.desafiodev.model.Transacao;
import br.com.bycoderstec.desafiodev.service.ProcessamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CnabController {

    private final ProcessamentoService processamentoService;

    public CnabController(ProcessamentoService processamentoService) {
        this.processamentoService = processamentoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<List<Transacao>> uploadFile(@RequestBody MultipartFile file) throws IOException {
        List<Transacao> transacoesSalvas = processamentoService.processarDados(file.getInputStream());
        return ResponseEntity.ok(transacoesSalvas);
    }
}
