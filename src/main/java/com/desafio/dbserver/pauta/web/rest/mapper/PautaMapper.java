package com.desafio.dbserver.pauta.web.rest.mapper;

import com.desafio.dbserver.pauta.domain.Pauta;
import com.desafio.dbserver.pauta.web.rest.dto.PautaDTO;

public class PautaMapper {
    public static Pauta toEntity(PautaDTO pautaDTO) {
        return Pauta.builder()
                .id(pautaDTO.id())
                .status(pautaDTO.status())
                .tempoLimite(pautaDTO.tempoLimite())
                .titulo(pautaDTO.titulo())
                .build();
    }

    public static PautaDTO toDto(Pauta pauta) {
        return PautaDTO.builder()
                .id(pauta.getId())
                .status(pauta.getStatus())
                .tempoLimite(pauta.getTempoLimite())
                .titulo(pauta.getTitulo())
                .build();
    }
}
