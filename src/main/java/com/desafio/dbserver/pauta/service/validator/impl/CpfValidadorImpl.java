package com.desafio.dbserver.pauta.service.validator.impl;


import com.desafio.dbserver.pauta.service.validator.CpfServiceClient;
import com.desafio.dbserver.pauta.service.validator.CpfValidador;
import com.desafio.dbserver.pauta.web.rest.dto.CpfDTO;
import com.desafio.dbserver.pauta.web.rest.exception.CpfInvalidoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import static com.desafio.dbserver.pauta.utils.Constantes.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CpfValidadorImpl implements CpfValidador {

    private final CpfServiceClient cpfServiceClient;

    public String valida(String cpf) {
        log.info("validando CPF: {}", cpf);
        CpfDTO resposta = buscarCpf(cpf);
        ehApto(resposta);
        return resposta.cpf();
    }



    private CpfDTO buscarCpf(String cpf) {
        try {
            return cpfServiceClient.getCpf(cpf);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new CpfInvalidoException(CPF_INVALIDO_EXCEPTION);
            }
            throw new CpfInvalidoException("An error occurred while trying to access 'CpfService'.");
        }
    }

    private void ehApto(CpfDTO resposta) {
        log.info("Resposta cpfService : {}", resposta);
        if (!resposta.status().equals(ABLE_TO_VOTE)) {
            throw new CpfInvalidoException(CPF_NAO_DISPONIVEL_EXCEPTION);
        }
    }
}
