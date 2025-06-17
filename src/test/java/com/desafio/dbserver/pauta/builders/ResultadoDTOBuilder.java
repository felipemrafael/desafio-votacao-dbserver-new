package com.desafio.dbserver.pauta.builders;

import com.desafio.dbserver.pauta.web.rest.dto.ResultadoDTO;

import static com.desafio.dbserver.pauta.utils.Constantes.SIM;

public class ResultadoDTOBuilder {

    public static ResultadoDTO umResultadoDTO(){
        return ResultadoDTO.builder()
                .seqPauta(1L)
                .titulo("coxinha > all")
                .quantidadeSim(100)
                .quantidadeNao(1)
                .resultado(SIM)
                .status("FECHADA")
                .build();
    }
}
