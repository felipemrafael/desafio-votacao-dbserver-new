package com.desafio.dbserver.pauta.service;

import com.desafio.dbserver.pauta.domain.Associado;
import com.desafio.dbserver.pauta.domain.Pauta;
import com.desafio.dbserver.pauta.web.rest.dto.SessaoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssociadoService {
    Associado cadastrar(Associado toEntity);

    List<Associado> listaAssociados();
}
