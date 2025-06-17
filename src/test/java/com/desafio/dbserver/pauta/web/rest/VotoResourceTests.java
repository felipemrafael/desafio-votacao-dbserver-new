package com.desafio.dbserver.pauta.web.rest;

import com.desafio.dbserver.pauta.builders.voto.VotoBuilder;
import com.desafio.dbserver.pauta.builders.voto.VotoDTOBuilder;
import com.desafio.dbserver.pauta.domain.Voto;
import com.desafio.dbserver.pauta.resource.BaseTest;
import com.desafio.dbserver.pauta.service.VotoService;
import org.junit.jupiter.api.BeforeEach;
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
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = {VotoResource.class})
public class VotoResourceTests extends BaseTest {

    @Autowired
    private VotoResource votoResource;

    @MockBean
    private VotoService votoService;

    @BeforeEach
    public void setUp() {
        standaloneSetup(votoResource);
    }

    @Test
    void deveCadastrarUmVotoComMockMvc() throws Exception {
        Voto voto = VotoBuilder.umVoto();
        Mockito.when(this.votoService.cadastrar(any())).thenReturn(voto);

        mockMvc.perform(MockMvcRequestBuilders.post("/votos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(VotoDTOBuilder.umVotoDTO())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


}
