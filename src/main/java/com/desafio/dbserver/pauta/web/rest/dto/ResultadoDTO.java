package com.desafio.dbserver.pauta.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ResultadoDTO (@JsonProperty("id_pauta") Long seqPauta, String titulo, String status,
                            @JsonProperty("quantidade_sim") Integer quantidadeSim, @JsonProperty("quantidade_nao") Integer quantidadeNao,
                            String resultado) {}
