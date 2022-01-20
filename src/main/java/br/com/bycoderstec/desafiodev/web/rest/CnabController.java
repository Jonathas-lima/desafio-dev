package br.com.bycoderstec.desafiodev.web.rest;

import br.com.bycoderstec.desafiodev.model.Transacao;
import br.com.bycoderstec.desafiodev.service.ProcessamentoService;
import br.com.bycoderstec.desafiodev.web.rest.response.UploadFileResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CnabController {

    private final ProcessamentoService processamentoService;

    public CnabController(ProcessamentoService processamentoService) {
        this.processamentoService = processamentoService;
    }

    @PostMapping("/upload")
    public ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        List<Integer> transacoesSalvas = processamentoService.processarDados(file.getInputStream())
                .stream()
                .map(Transacao::getId)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new UploadFileResponse(transacoesSalvas));
    }
}
