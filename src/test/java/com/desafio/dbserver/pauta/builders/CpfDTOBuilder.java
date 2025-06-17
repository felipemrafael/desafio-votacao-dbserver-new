package com.desafio.dbserver.pauta.builders;

import com.desafio.dbserver.pauta.web.rest.dto.CpfDTO;

import static com.desafio.dbserver.pauta.utils.Constantes.ABLE_TO_VOTE;
import static com.desafio.dbserver.pauta.utils.Constantes.UNABLE_TO_VOTE;

public class CpfDTOBuilder {

    public static CpfDTO umCpfDTOInvalido(){
        return CpfDTO.builder()
                .cpf("99999999999")
                .status(UNABLE_TO_VOTE)
                .build();
    }

    public static CpfDTO umCpfDTOValido(){
        return CpfDTO.builder()
                .cpf("88888888888")
                .status(ABLE_TO_VOTE)
                .build();
    }
}
