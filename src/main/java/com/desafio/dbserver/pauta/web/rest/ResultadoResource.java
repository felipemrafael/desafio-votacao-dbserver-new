package com.desafio.dbserver.pauta.web.rest;

import com.desafio.dbserver.pauta.service.ResultadoService;
import com.desafio.dbserver.pauta.web.rest.dto.ResultadoDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping
public class ResultadoResource {

    private final ResultadoService resultadoService;

    @GetMapping(value = "/resultados/{id}")
    public ResponseEntity<?> obterResultado(@PathVariable Long id) {
        ResultadoDTO resultadoDTO = resultadoService.obterResultado(id);
        return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
    }
}
