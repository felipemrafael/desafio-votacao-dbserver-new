package com.desafio.dbserver.pauta.service.validator;

import com.desafio.dbserver.pauta.config.CpfConfig;
import com.desafio.dbserver.pauta.service.validator.impl.CpfValidadorImpl;
import com.desafio.dbserver.pauta.web.rest.dto.CpfDTO;
import com.desafio.dbserver.pauta.web.rest.exception.CpfInvalidoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static com.desafio.dbserver.pauta.builders.CpfDTOBuilder.umCpfDTOInvalido;
import static com.desafio.dbserver.pauta.builders.CpfDTOBuilder.umCpfDTOValido;
import static java.lang.String.format;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class CpfValidatorImplTests {

    @Mock
    private CpfValidador cpfValidador;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private CpfServiceClient cpfServiceClient;

    String cpf;

    @BeforeEach
    public void setUp() {
        cpf = "16000519010";
        restTemplate = mock(RestTemplate.class);
        cpfServiceClient = mock(CpfServiceClient.class);
        cpfValidador = new CpfValidadorImpl(cpfServiceClient);
    }

    @Test
    @DisplayName("cpf deve ser válido")
    public void cpfDeveSerValido() {
        var cpf = "16000519010";
        Mockito.when(this.cpfServiceClient.getCpf(any())).thenReturn(umCpfDTOValido());
        cpfValidador.valida(cpf);
    }

    @Test
    @DisplayName("deve lançar Exceção ao validar CPF Inválido")
    public void cpfDeveSerInvalido() {
        Mockito.when(this.cpfServiceClient.getCpf(any())).thenReturn(umCpfDTOInvalido());

        Assertions.assertThrows(CpfInvalidoException.class, ()->{
           cpfValidador.valida(cpf);
        });
    }



}
