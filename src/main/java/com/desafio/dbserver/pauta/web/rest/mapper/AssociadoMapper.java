package com.desafio.dbserver.pauta.web.rest.mapper;

import com.desafio.dbserver.pauta.domain.Associado;
import com.desafio.dbserver.pauta.web.rest.dto.AssociadoDTO;

public class AssociadoMapper {
    public static Associado toEntity(AssociadoDTO associadoDTO) {
        return Associado.builder()
                .id(associadoDTO.id())
                .cpf(associadoDTO.cpf())
                .nome(associadoDTO.nome())
                .build();
    }

    public static AssociadoDTO toDto(Associado associado) {
        return AssociadoDTO.builder()
                .id(associado.getId())
                .cpf(associado.getCpf())
                .build();
    }
}
