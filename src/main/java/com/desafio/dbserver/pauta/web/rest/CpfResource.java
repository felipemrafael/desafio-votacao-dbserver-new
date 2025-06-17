package com.desafio.dbserver.pauta.web.rest;

import com.desafio.dbserver.pauta.web.rest.dto.CpfDTO;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.intellij.lang.annotations.JdkConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

import static com.desafio.dbserver.pauta.utils.Constantes.ABLE_TO_VOTE;
import static com.desafio.dbserver.pauta.utils.Constantes.UNABLE_TO_VOTE;


@RestController
@AllArgsConstructor
@RequestMapping
public class CpfResource {

    private static final Random RANDOM = new Random();

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<CpfDTO> getCpf(@PathVariable String cpf) {
        var status = RANDOM.nextBoolean() ? ABLE_TO_VOTE : UNABLE_TO_VOTE;
        return new ResponseEntity<>(new CpfDTO(cpf, status), HttpStatus.OK);
    }

}
