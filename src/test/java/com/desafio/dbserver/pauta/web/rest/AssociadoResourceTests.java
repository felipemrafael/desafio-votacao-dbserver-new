package com.desafio.dbserver.pauta.web.rest;

import com.desafio.dbserver.pauta.resource.BaseTest;
import com.desafio.dbserver.pauta.service.AssociadoService;
import com.desafio.dbserver.pauta.web.rest.dto.AssociadoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.List;

import static com.desafio.dbserver.pauta.builders.associado.AssociadoBuilder.umAssociado;
import static com.desafio.dbserver.pauta.builders.associado.AssociadoDTOBuilder.umAssociadoDTO;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = {AssociadoResource.class})
public class AssociadoResourceTests extends BaseTest {

    @Autowired
    private AssociadoResource associadoResource;

    @MockBean
    private AssociadoService associadoService;

    @BeforeEach
    public void setUp() {
        standaloneSetup(this.associadoService);
    }

    @Test
    void deveCadastrarAssociadoComMockMvc() throws Exception {
        AssociadoDTO associadoDTO = umAssociadoDTO();
        Mockito.when(this.associadoService.cadastrar(any())).thenReturn(umAssociado());

        mockMvc.perform(MockMvcRequestBuilders.post("/associado")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(associadoDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("deve obter a lista de associados com sucesso")
    public void deveObterListaAssociadoComSucesso() throws Exception {
        Mockito.when(associadoService.listaAssociados()).thenReturn(List.of(umAssociado()));

        mockMvc.perform(MockMvcRequestBuilders.get("/associado")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
