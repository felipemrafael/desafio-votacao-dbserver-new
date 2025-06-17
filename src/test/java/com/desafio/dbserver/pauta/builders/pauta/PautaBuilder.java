package com.desafio.dbserver.pauta.builders.pauta;

import com.desafio.dbserver.pauta.domain.Pauta;
import com.desafio.dbserver.pauta.domain.VotoEnum;

import java.time.LocalDateTime;
import java.util.List;

import static com.desafio.dbserver.pauta.builders.voto.VotoBuilder.umVoto;
import static com.desafio.dbserver.pauta.utils.Constantes.*;
import static java.util.Set.of;

public class PautaBuilder {
    public static Pauta umaPautaFechada() {
        return Pauta.builder()
                .id(1L)
                .titulo("coxinha > all")
                .tempoLimite(LocalDateTime.now())
                .status(FECHADA).build();
    }

    public static Pauta umaSemTituloPauta() {
        return Pauta.builder()
                .id(1L)
                .tempoLimite(LocalDateTime.now())
                .status(FECHADA).build();
    }

    public static Pauta umaPautaFechadaPorTempo() {
        return Pauta.builder()
                .id(1L)
                .titulo("coxinha > all")
                .tempoLimite(LocalDateTime.now().minusMinutes(1))
                .status(ABERTA).build();
    }

    public static Pauta umaPautaFechadaIhNaoEnviada() {
        return Pauta.builder()
                .enviadoKafka(false)
                .status(FECHADA)
                .build();
    }

    public static Pauta umaPautaSemStatus() {
        return Pauta.builder()
                .titulo("coxinha > all")
                .build();
    }

    public static Pauta umaPautaAberta() {
        return Pauta.builder()
                .id(1L)
                .titulo("coxinha > all")
                .status(ABERTA)
                .tempoLimite(LocalDateTime.now().plusMinutes(2))
                .build();
    }

    public static List<Pauta> umaListaDePautas() {
        return List.of(umaPautaAberta());
    }

    public static Pauta umaPautaComMaisVotosSim() {
        return Pauta.builder()
                .id(1L)
                .titulo("coxinha > all")
                .votos(of(umVoto(VotoEnum.SIM), umVoto(VotoEnum.SIM), umVoto(VotoEnum.SIM), umVoto(VotoEnum.NAO), umVoto(VotoEnum.NAO)))
                .tempoLimite(LocalDateTime.now().minusMinutes(1))
                .status(FECHADA)
                .build();
    }

    public static Pauta umaPautaEmpatada() {
        return Pauta.builder()
                .id(1L)
                .titulo("coxinha > all")
                .votos(of(umVoto(VotoEnum.SIM), umVoto(VotoEnum.SIM), umVoto(VotoEnum.SIM), umVoto(VotoEnum.NAO), umVoto(VotoEnum.NAO), umVoto(VotoEnum.NAO)))
                .tempoLimite(LocalDateTime.now().minusMinutes(1))
                .status(FECHADA)
                .build();
    }

    public static Pauta umaPautaComMaisVotosNao() {
        return Pauta.builder()
                .id(1L)
                .titulo("coxinha > all")
                .votos(of(umVoto(VotoEnum.SIM), umVoto(VotoEnum.SIM), umVoto(VotoEnum.NAO), umVoto(VotoEnum.NAO), umVoto(VotoEnum.NAO)))
                .tempoLimite(LocalDateTime.now().minusMinutes(1))
                .status(FECHADA)
                .build();
    }

}
