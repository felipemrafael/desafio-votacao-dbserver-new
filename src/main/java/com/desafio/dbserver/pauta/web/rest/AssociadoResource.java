package com.desafio.dbserver.pauta.web.rest;

import com.desafio.dbserver.pauta.service.AssociadoService;
import com.desafio.dbserver.pauta.web.rest.dto.AssociadoDTO;
import com.desafio.dbserver.pauta.web.rest.mapper.AssociadoMapper;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.message.ApiVersionsResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static com.desafio.dbserver.pauta.web.rest.mapper.AssociadoMapper.toDto;

@RestController
@AllArgsConstructor
@RequestMapping("/associado")
public class AssociadoResource {

    private final AssociadoService associadoService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody AssociadoDTO associadoDTO) {
        var associado = associadoService.cadastrar(AssociadoMapper.toEntity(associadoDTO));
        return new ResponseEntity<>(toDto(associado), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        var listaAssociados = associadoService.listaAssociados();
        var listaDTO = listaAssociados.stream().map(AssociadoMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(listaDTO, HttpStatus.OK);
    }
}
