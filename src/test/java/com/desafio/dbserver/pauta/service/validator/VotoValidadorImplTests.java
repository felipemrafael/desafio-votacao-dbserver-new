package com.desafio.dbserver.pauta.service.validator;

import com.desafio.dbserver.pauta.domain.VotoEnum;
import com.desafio.dbserver.pauta.domain.VotoPK;
import com.desafio.dbserver.pauta.repository.VotoRepository;
import com.desafio.dbserver.pauta.service.validator.impl.VotoValidadorImpl;
import com.desafio.dbserver.pauta.web.rest.exception.VotoDuplicadoException;
import com.desafio.dbserver.pauta.web.rest.exception.VotoInvalidoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.desafio.dbserver.pauta.builders.voto.VotoBuilder.umVoto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class VotoValidadorImplTests {

    @Mock
    private VotoValidador votoValidador;

    @MockBean
    private CpfValidador cpfValidador;

    @MockBean
    private VotoRepository votoRepository;

    @BeforeEach
    public void setUp() {
        cpfValidador = mock(CpfValidador.class);
        votoRepository = mock(VotoRepository.class);
        this.votoValidador = new VotoValidadorImpl(votoRepository, cpfValidador);
    }

    @Test
    @DisplayName("não deve lançar exceção ao validar CPF")
    public void naoDeveLancarExcecaoAoValidar(){
        Mockito.when(this.votoRepository.findById(any(VotoPK.class))).thenReturn(Optional.empty());

        votoValidador.validar(umVoto());
    }

    @Test
    @DisplayName("deve lançar exceção ao validar voto duplicado")
    public void deveLancarExcecaoAoValidarVotoDuplicado(){
        Mockito.when(this.votoRepository.findById(any(VotoPK.class))).thenReturn(Optional.of(umVoto()));

        Assertions.assertThrows(VotoDuplicadoException.class, ()->{
            votoValidador.validar(umVoto());
        });
    }

    @Test
    @DisplayName("não deve lançar exceção ao validar voto Sim válido")
    public void naoDeveLancarExcecaoAoValidarVotoSimValido(){
        Mockito.when(this.votoRepository.findById(any(VotoPK.class))).thenReturn(Optional.empty());

        votoValidador.validar(umVoto(VotoEnum.SIM));
    }

    @Test
    @DisplayName("não deve lançar exceção ao validar voto Não válido")
    public void naoDeveLancarExcecaoAoValidarVotoNaoValido(){
        Mockito.when(this.votoRepository.findById(any(VotoPK.class))).thenReturn(Optional.empty());

        votoValidador.validar(umVoto(VotoEnum.NAO));
    }



}
