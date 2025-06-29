package com.desafio.dbserver.pauta.builders;

import com.desafio.dbserver.pauta.web.rest.dto.SessaoDTO;

public class SessaoDTOBuilder {

    public static SessaoDTO umaSessaoComMinuto(){
        return SessaoDTO.builder()
                .idPauta(1L)
                .minutos(1)
                .build();
    }
    public static SessaoDTO umaSessaoComMinuto(Integer minutos){
        return SessaoDTO.builder()
                .idPauta(1L)
                .minutos(minutos)
                .build();
    }

    public static SessaoDTO umaSessaoSemMinuto(){
        return SessaoDTO.builder().build();
    }
}
