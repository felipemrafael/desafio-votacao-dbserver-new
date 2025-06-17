package com.desafio.dbserver.pauta.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PautaDTO (@JsonProperty("id_pauta") Long id, String titulo, String status, @JsonProperty("datahora_limite")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    LocalDateTime tempoLimite){}
