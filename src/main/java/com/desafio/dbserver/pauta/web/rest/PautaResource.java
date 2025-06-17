package com.desafio.dbserver.pauta.web.rest;

import com.desafio.dbserver.pauta.domain.Pauta;
import com.desafio.dbserver.pauta.service.PautaService;
import com.desafio.dbserver.pauta.web.rest.dto.PautaDTO;
import com.desafio.dbserver.pauta.web.rest.dto.SessaoDTO;
import com.desafio.dbserver.pauta.web.rest.mapper.PautaMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.desafio.dbserver.pauta.web.rest.mapper.PautaMapper.toDto;

@RestController
@AllArgsConstructor
@RequestMapping("/pauta")
public class PautaResource {

    private final PautaService pautaService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody PautaDTO pautaDTO) {
        Pauta pautaCadastrada = pautaService.cadastrar(PautaMapper.toEntity(pautaDTO));
        return new ResponseEntity<>(toDto(pautaCadastrada), HttpStatus.CREATED);
    }

    @PostMapping(value = "/abrir")
    public ResponseEntity<?> abrirVotacao(@RequestBody SessaoDTO sessaoDTO) {
        Pauta pautaAberta = pautaService.abrirVotacao(sessaoDTO);
        return new ResponseEntity<>(toDto(pautaAberta), HttpStatus.ACCEPTED);
    }
}
