package com.desafio.dbserver.pauta.builders.voto;

import com.desafio.dbserver.pauta.domain.VotoEnum;
import com.desafio.dbserver.pauta.web.rest.dto.VotoDTO;

public class VotoDTOBuilder {

    public static VotoDTO umVotoDTO() {
        return VotoDTO.builder()
                .cpf("10338927425")
                .idAssociado(1L)
                .idPauta(1L)
                .voto(VotoEnum.SIM)
                .build();
    }

    public static VotoDTO umVotoDTO(VotoEnum voto) {
        return VotoDTO.builder()
                .cpf("10338927425")
                .idAssociado(1L)
                .idPauta(1L)
                .voto(voto)
                .build();
    }
}
