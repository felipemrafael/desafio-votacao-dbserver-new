package com.desafio.dbserver.pauta.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotoPK implements Serializable {

    private Long idPauta;
    private Long idAssociado;
}
