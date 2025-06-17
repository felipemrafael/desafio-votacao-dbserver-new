package com.desafio.dbserver.pauta.service.impl;

import com.desafio.dbserver.pauta.service.PautaService;
import com.desafio.dbserver.pauta.service.ResultadoService;
import com.desafio.dbserver.pauta.web.rest.dto.ResultadoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.desafio.dbserver.pauta.builders.pauta.PautaBuilder.*;
import static com.desafio.dbserver.pauta.utils.Constantes.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class ResultadoServiceImplTests {

    private ResultadoService resultadoService;

    private PautaService pautaService;

    @BeforeEach
    public void setUp() {
        pautaService = mock(PautaServiceImpl.class);
        resultadoService = new ResultadoServiceImpl(pautaService);
    }

    @Test
    @DisplayName("resultado da pauta deve ser Sim")
    public void resultadoDaPautaDeveSerSim() {
        Mockito.when(pautaService.buscarPorId(any(Long.class))).thenReturn(umaPautaComMaisVotosSim());

        ResultadoDTO resultadoDTO = resultadoService.obterResultado(1L);
        Assertions.assertEquals(resultadoDTO.resultado(), SIM);
    }

    @Test
    @DisplayName("resultado da pauta deve ser Não")
    public void resultadoDaPautaDeveSerNao() {
        Mockito.when(pautaService.buscarPorId(any(Long.class))).thenReturn(umaPautaComMaisVotosNao());
       // Mockito.when(resultadoService.obterResultado(1L)).thenReturn(dto);

        ResultadoDTO resultadoDTO = resultadoService.obterResultado(1L);
        Assertions.assertEquals(resultadoDTO.resultado(), NAO);
    }

    @Test
    @DisplayName("resultado da pauta deve ser Empate")
    public void resultadoDaPautaDeveSerEmpate() {
        Mockito.when(pautaService.buscarPorId(any(Long.class))).thenReturn(umaPautaEmpatada());

        ResultadoDTO resultadoDTO = resultadoService.obterResultado(1L);
        Assertions.assertEquals(resultadoDTO.resultado(), EMPATE);
    }
}
