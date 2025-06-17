package com.desafio.dbserver.pauta.builders.pauta;

import com.desafio.dbserver.pauta.web.rest.dto.PautaDTO;

public class PautaDTOBuilder {

    public static PautaDTO umaPautaDTO(){
        return PautaDTO.builder()
                .id(1L)
                .titulo("coxinha > all")
                .build();
    }

    public static PautaDTO umaPautaSemTituloDTO(){
        return PautaDTO.builder()
                .id(1L)
                .build();
    }
}
