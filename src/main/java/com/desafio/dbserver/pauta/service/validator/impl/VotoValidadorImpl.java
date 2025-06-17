package com.desafio.dbserver.pauta.service.validator.impl;

import com.desafio.dbserver.pauta.domain.Voto;
import com.desafio.dbserver.pauta.domain.VotoEnum;
import com.desafio.dbserver.pauta.domain.VotoPK;
import com.desafio.dbserver.pauta.repository.VotoRepository;
import com.desafio.dbserver.pauta.service.validator.CpfValidador;
import com.desafio.dbserver.pauta.service.validator.VotoValidador;
import com.desafio.dbserver.pauta.web.rest.exception.VotoDuplicadoException;
import com.desafio.dbserver.pauta.web.rest.exception.VotoInvalidoException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.desafio.dbserver.pauta.utils.Constantes.*;

@Service
@AllArgsConstructor
@Slf4j
public class VotoValidadorImpl implements VotoValidador {

    private final VotoRepository votoRepository;
    private final CpfValidador cpfValidador;

    @Override
    public void validar(Voto voto) {
        log.info("validando voto: {} ", voto);
        Optional<Voto> votoConsultado = votoRepository.findById(obterVotoId(voto));
        if (votoConsultado.isPresent()) {
            throw new VotoDuplicadoException(VOTO_DUPLICADO_EXCEPTION);
        }
        cpfValidador.valida(voto.getCpf());
    }

    private VotoPK obterVotoId(Voto voto) {
        return VotoPK.builder()
                .idAssociado(voto.getIdAssociado())
                .idPauta(voto.getIdPauta())
                .build();
    }


}
