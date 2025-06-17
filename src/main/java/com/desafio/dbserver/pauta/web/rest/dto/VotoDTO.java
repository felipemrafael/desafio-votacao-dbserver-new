package com.desafio.dbserver.pauta.web.rest.dto;

import com.desafio.dbserver.pauta.domain.VotoEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record VotoDTO(@JsonProperty("id_pauta") Long idPauta, @JsonProperty("id_associado") Long idAssociado, String cpf,
                      VotoEnum voto){}
