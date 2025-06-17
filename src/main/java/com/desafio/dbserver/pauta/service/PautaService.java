package com.desafio.dbserver.pauta.service;

import com.desafio.dbserver.pauta.domain.Pauta;
import com.desafio.dbserver.pauta.web.rest.dto.SessaoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PautaService {
    Pauta cadastrar(Pauta toEntity);

    Pauta abrirVotacao(SessaoDTO sessaoDTO);

    Pauta buscarPorId(Long id);

    List<Pauta> consultarPautasAbertas();

    Pauta atualizarPauta(Pauta pauta);
}
