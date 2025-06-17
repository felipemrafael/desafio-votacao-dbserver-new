package com.desafio.dbserver.pauta.builders.associado;

import com.desafio.dbserver.pauta.web.rest.dto.AssociadoDTO;
import com.desafio.dbserver.pauta.web.rest.dto.PautaDTO;

public class AssociadoDTOBuilder {

    public static AssociadoDTO umAssociadoDTO(){
        return AssociadoDTO.builder()
                .id(1L)
                .nome("teste")
                .cpf("99999999999")
                .build();
    }
}
