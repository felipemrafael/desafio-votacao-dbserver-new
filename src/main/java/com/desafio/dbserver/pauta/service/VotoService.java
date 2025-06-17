package com.desafio.dbserver.pauta.service;

import com.desafio.dbserver.pauta.domain.Voto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface VotoService {
    Optional<Voto> buscarPorId(Voto voto);

    Voto cadastrar(Voto voto);
}
