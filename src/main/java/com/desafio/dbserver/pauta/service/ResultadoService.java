package com.desafio.dbserver.pauta.service;

import com.desafio.dbserver.pauta.web.rest.dto.ResultadoDTO;
import org.springframework.stereotype.Service;

@Service
public interface ResultadoService {
    ResultadoDTO obterResultado(Long id);
}
