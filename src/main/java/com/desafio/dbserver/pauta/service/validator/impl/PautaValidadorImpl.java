package com.desafio.dbserver.pauta.service.validator.impl;

import com.desafio.dbserver.pauta.domain.Pauta;
import com.desafio.dbserver.pauta.service.PautaService;
import com.desafio.dbserver.pauta.service.validator.PautaValidador;
import com.desafio.dbserver.pauta.web.rest.exception.SessaoFechadaException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.desafio.dbserver.pauta.utils.Constantes.SESSAO_FECHADA_EXCEPTION;

@Service
@Slf4j
@AllArgsConstructor
public class PautaValidadorImpl implements PautaValidador {


    private final PautaService pautaService;

    @Override
    public void validar(Long idPauta) {
        log.info("validando pauta nº: {}", idPauta);
        Pauta pauta = pautaService.buscarPorId(idPauta);
        if (pauta.estahFechada()) {
            throw new SessaoFechadaException(SESSAO_FECHADA_EXCEPTION);
        }
    }

}
