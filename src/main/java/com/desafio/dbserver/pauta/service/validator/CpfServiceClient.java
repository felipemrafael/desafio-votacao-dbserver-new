package com.desafio.dbserver.pauta.service.validator;

import com.desafio.dbserver.pauta.web.rest.dto.CpfDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface CpfServiceClient {
    @GetExchange("/v1/cpf/{cpf}")
    CpfDTO getCpf(@PathVariable String cpf);
}
