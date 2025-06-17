package com.desafio.dbserver.pauta.service.impl;

import com.desafio.dbserver.pauta.domain.Voto;
import com.desafio.dbserver.pauta.domain.VotoPK;
import com.desafio.dbserver.pauta.repository.VotoRepository;
import com.desafio.dbserver.pauta.service.VotoService;
import com.desafio.dbserver.pauta.service.validator.PautaValidador;
import com.desafio.dbserver.pauta.service.validator.VotoValidador;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class VotoServiceImpl implements VotoService {

    private final VotoRepository votoRepository;
    private final VotoValidador votoValidador;
    private final PautaValidador pautaValidador;

    @Override
    public Optional<Voto> buscarPorId(Voto voto){
        return votoRepository.findById(obterVotoId(voto));
    }

    @Override
    public Voto cadastrar(Voto voto) {
        votoValidador.validar(voto);
        pautaValidador.validar(voto.getIdPauta());
        log.info("cadastrando novo voto: " + voto);
        return votoRepository.save(voto);
    }

    private VotoPK obterVotoId(Voto voto) {
        return VotoPK.builder()
                .idAssociado(1L)
                .idPauta(voto.getIdPauta())
                .build();
    }
}
