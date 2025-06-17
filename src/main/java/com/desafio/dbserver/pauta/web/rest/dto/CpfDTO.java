package com.desafio.dbserver.pauta.web.rest.dto;


import com.desafio.dbserver.pauta.utils.validation.Cpf;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;


@Builder
public record CpfDTO (@Cpf String cpf, @JsonProperty("status") String status) {}

