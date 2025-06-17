package com.desafio.dbserver.pauta.web.rest.dto;

import lombok.Builder;

@Builder
public record AssociadoDTO(Long id, String cpf, String nome){}
