package com.desafio.dbserver.pauta.web.rest;

import com.desafio.dbserver.pauta.domain.Voto;
import com.desafio.dbserver.pauta.service.VotoService;
import com.desafio.dbserver.pauta.web.rest.dto.VotoDTO;
import com.desafio.dbserver.pauta.web.rest.mapper.VotoMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/votos")
public class VotoResource {

    private final VotoService votoService;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody VotoDTO votoDTO) {
        Voto votoCadastrado = votoService.cadastrar(VotoMapper.toEntity(votoDTO));
        return new ResponseEntity<>(VotoMapper.toDto(votoCadastrado), HttpStatus.CREATED);
    }
}
