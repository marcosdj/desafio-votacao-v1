package com.db.desafio_votacao_v1.resource;

import com.db.desafio_votacao_v1.dto.PautaRecordRequest;
import com.db.desafio_votacao_v1.service.PautaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pauta")
public class PautaResource {
    private final PautaService pautaService;

    public PautaResource(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping
    public ResponseEntity cadastrarPauta(@RequestBody @Valid PautaRecordRequest pautaRecordRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pautaService.cadastrarPauta(pautaRecordRequest));
    }

    @GetMapping("{id}")
    public ResponseEntity buscarPauta(@PathVariable("id") Long idPauta) {
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.buscarPautaResponse(idPauta));
    }

    @GetMapping("resultado/{id}")
    public  ResponseEntity obterResultado(@PathVariable("id") Long idPauta) {
        return ResponseEntity.status(HttpStatus.OK).body(pautaService.obterResultado(idPauta));
    }
}
