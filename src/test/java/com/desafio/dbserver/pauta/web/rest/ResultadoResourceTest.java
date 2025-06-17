package com.desafio.dbserver.pauta.web.rest;

import com.desafio.dbserver.pauta.builders.ResultadoDTOBuilder;
import com.desafio.dbserver.pauta.resource.BaseTest;
import com.desafio.dbserver.pauta.service.ResultadoService;
import com.desafio.dbserver.pauta.web.rest.dto.ResultadoDTO;
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
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = {ResultadoResource.class})
class ResultadoResourceTest extends BaseTest {

    @Autowired
    private ResultadoResource resultadoResource;

    @MockBean
    private ResultadoService resultadoService;

    @BeforeEach
    public void setUp() {
        standaloneSetup(this.resultadoService);
    }

    @Test
    void obterResultadoComSucesso() throws Exception {
        ResultadoDTO resultadoDTO = ResultadoDTOBuilder.umResultadoDTO();
        Mockito.when(resultadoService.obterResultado(1L)).thenReturn(resultadoDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/resultados/1")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(resultadoDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("deve retornar erro 404 ao tentar obter resultado com ID inexistente")
    @Test
    void obterResultadoComIdInexistenteRetornaErro() throws Exception {
        Mockito.when(resultadoService.obterResultado(999L)).thenThrow(new PautaNaoEncontradaException("Resultado não encontrado"));

        mockMvc.perform(MockMvcRequestBuilders.get("/resultados/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @DisplayName("deve retornar erro 400 ao tentar obter resultado com ID inválido")
    @Test
    void obterResultadoComIdInvalidoRetornaErro() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/resultados/abc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}