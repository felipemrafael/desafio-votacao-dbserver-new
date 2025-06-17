package com.desafio.dbserver.pauta.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record SessaoDTO (@JsonProperty("id_pauta") Long idPauta, Integer minutos){}
