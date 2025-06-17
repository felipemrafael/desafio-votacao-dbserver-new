package com.desafio.dbserver.pauta.service.impl;

import com.desafio.dbserver.pauta.domain.Pauta;
import com.desafio.dbserver.pauta.domain.Voto;
import com.desafio.dbserver.pauta.domain.VotoEnum;
import com.desafio.dbserver.pauta.service.PautaService;
import com.desafio.dbserver.pauta.service.ResultadoService;
import com.desafio.dbserver.pauta.web.rest.dto.ResultadoDTO;
import com.desafio.dbserver.pauta.web.rest.exception.PautaNaoEncontradaException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.desafio.dbserver.pauta.utils.Constantes.*;

@Component
@AllArgsConstructor
public class ResultadoServiceImpl implements ResultadoService {

    private static final Logger logger = LoggerFactory.getLogger(ResultadoServiceImpl.class);

    private final PautaService pautaService;

    @Override
    public ResultadoDTO obterResultado(Long id) {
        logger.info("obtendo resultado de pauta numero: " + id);
        Pauta pauta = pautaService.buscarPorId(id);
        if(pauta == null) {
            logger.error("Pauta não encontrada para o id: " + id);
            throw new PautaNaoEncontradaException(PAUTA_NAO_ENCONTRADA_EXCEPTION);
        }
        return construirResultado(pauta);
    }

    private ResultadoDTO construirResultado(Pauta pauta) {
        Integer quantidadeSim = obterQuantidadePorOpcao(pauta.getVotos(), VotoEnum.SIM);
        Integer quantidadeNao = obterQuantidadePorOpcao(pauta.getVotos(), VotoEnum.NAO);

        return ResultadoDTO.builder()
                .seqPauta(pauta.getId())
                .titulo(pauta.getTitulo())
                .quantidadeNao(quantidadeNao)
                .quantidadeSim(quantidadeSim)
                .status(pauta.getStatus())
                .resultado(calcularVotos(quantidadeSim, quantidadeNao))
                .build();
    }

    private String calcularVotos(Integer quantidadeSim, Integer quantidadeNao) {
        if (quantidadeNao.equals(quantidadeSim)) {
            return EMPATE;
        } else if (quantidadeNao > quantidadeSim) {
            return NAO;
        } else {
            return SIM;
        }
    }

    private Integer obterQuantidadePorOpcao(Set<Voto> votos, VotoEnum opcao) {
        List<Voto> votosFiltrados = votos.stream().filter(voto -> voto.getVoto().equals(opcao)).collect(Collectors.toList());
        return votosFiltrados.size();
    }
}
