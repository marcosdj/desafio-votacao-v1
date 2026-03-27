package com.db.desafio_votacao_v1.resource;

import com.db.desafio_votacao_v1.dto.SessaoRecordRequest;
import com.db.desafio_votacao_v1.service.SessaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sessao")
@RequiredArgsConstructor
public class SessaoResource {
    private final SessaoService sessaoService;

    @PostMapping
    public ResponseEntity abrirSessao(@RequestBody @Valid SessaoRecordRequest sessaoRecordRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessaoService.abrirSessao(sessaoRecordRequest));
    }

    @GetMapping("{id}")
    public ResponseEntity buscarSessao(@PathVariable("id") Long idSessao) {
        return ResponseEntity.status(HttpStatus.OK).body(sessaoService.buscarSessao(idSessao));
    }
}
