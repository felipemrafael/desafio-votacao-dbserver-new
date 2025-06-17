package com.desafio.dbserver.pauta.web.rest;

import com.desafio.dbserver.pauta.builders.SessaoDTOBuilder;
import com.desafio.dbserver.pauta.builders.pauta.PautaBuilder;
import com.desafio.dbserver.pauta.domain.Pauta;
import com.desafio.dbserver.pauta.resource.BaseTest;
import com.desafio.dbserver.pauta.service.PautaService;
import com.desafio.dbserver.pauta.web.rest.dto.SessaoDTO;
import com.desafio.dbserver.pauta.web.rest.exception.PautaNaoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

@SpringBootTest(classes = {PautaResource.class})
class PautaResourceTest extends BaseTest {

    @Autowired
    private PautaResource pautaResource;

    @MockBean
    private PautaService pautaService;

    @BeforeEach
    public void setUp() {
        standaloneSetup(this.pautaService);
    }

    @DisplayName("deve cadastrar uma nova pauta com sucesso")
    @Test
    void cadastrarNovaPautaComSucesso() throws Exception {
        Pauta pauta = PautaBuilder.umaPautaAberta();

        Mockito.when(pautaService.cadastrar(Mockito.any())).thenReturn(pauta);

        mockMvc.perform(MockMvcRequestBuilders.post("/pauta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(pauta)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id_pauta").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("coxinha > all"));
    }

    @DisplayName("deve abrir votação em uma pauta com sucesso")
    @Test
    void abrirVotacaoComSucesso() throws Exception {
        SessaoDTO sessaoDTO = SessaoDTOBuilder.umaSessaoComMinuto();
        Pauta pauta = PautaBuilder.umaPautaAberta();

        Mockito.when(pautaService.abrirVotacao(Mockito.any())).thenReturn(pauta);

        mockMvc.perform(MockMvcRequestBuilders.post("/pauta/abrir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(sessaoDTO)))
                .andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id_pauta").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("coxinha > all"));
    }

    @DisplayName("deve retornar erro 400 ao tentar abrir votação sem duração")
    @Test
    void abrirVotacaoSemDuracaoRetornaErro() throws Exception {
        SessaoDTO sessaoDTO = SessaoDTOBuilder.umaSessaoSemMinuto();
        Mockito.when(pautaService.abrirVotacao(sessaoDTO)).thenThrow(PautaNaoEncontradaException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/pauta/abrir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(sessaoDTO)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());


    }
}