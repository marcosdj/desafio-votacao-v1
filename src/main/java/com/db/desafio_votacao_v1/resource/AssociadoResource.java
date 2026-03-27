package com.db.desafio_votacao_v1.resource;

import com.db.desafio_votacao_v1.dto.AssociadoRecordRequest;
import com.db.desafio_votacao_v1.service.AssociadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("associado")
public class AssociadoResource {
    private final AssociadoService associadoService;

    public AssociadoResource(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @PostMapping
    public ResponseEntity cadastrarAssociado(@RequestBody @Valid AssociadoRecordRequest associadoRecordRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(associadoService.cadastrarAssociado(associadoRecordRequest.nome()));
    }

    @GetMapping("{id}")
    public ResponseEntity buscarAssociado(@PathVariable("id") @Valid Long idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(associadoService.buscarAssociado(idUsuario));
    }
}
