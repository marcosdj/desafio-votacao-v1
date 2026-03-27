package com.db.desafio_votacao_v1.resource;

import com.db.desafio_votacao_v1.dto.VotoRecordRequest;
import com.db.desafio_votacao_v1.service.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("voto")
@RequiredArgsConstructor
public class VotoResource {
    private final VotoService votoService;

    @PostMapping
    public ResponseEntity votar(@RequestBody VotoRecordRequest votoRecordRequest) {
        votoService.votar(votoRecordRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
