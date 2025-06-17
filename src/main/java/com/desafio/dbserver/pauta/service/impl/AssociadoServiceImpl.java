package com.desafio.dbserver.pauta.service.impl;

import com.desafio.dbserver.pauta.domain.Associado;
import com.desafio.dbserver.pauta.repository.AssociadoRepository;
import com.desafio.dbserver.pauta.service.AssociadoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AssociadoServiceImpl implements AssociadoService {

    private final AssociadoRepository associadoRepository;

    public Associado cadastrar(Associado entity) {
        return associadoRepository.save(entity);
    }

    public List<Associado> listaAssociados() {
        return associadoRepository.findAll();
    }
}
