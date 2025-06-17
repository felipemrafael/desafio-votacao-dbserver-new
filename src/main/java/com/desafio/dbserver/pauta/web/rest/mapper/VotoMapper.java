package com.desafio.dbserver.pauta.web.rest.mapper;

import com.desafio.dbserver.pauta.domain.Voto;
import com.desafio.dbserver.pauta.web.rest.dto.VotoDTO;

public class VotoMapper {
    public static Voto toEntity(VotoDTO votoDTO) {
        return Voto.builder()
                .cpf(votoDTO.cpf())
                .idPauta(votoDTO.idPauta())
                .idAssociado(votoDTO.idAssociado())
                .voto(votoDTO.voto())
                .build();
    }

    public static VotoDTO toDto(Voto voto) {
        return VotoDTO.builder()
                .cpf(voto.getCpf())
                .idPauta(voto.getIdPauta())
                .idAssociado(voto.getIdAssociado())
                .voto(voto.getVoto())
                .build();
    }
}
