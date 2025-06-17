package com.desafio.dbserver.pauta.builders.voto;

import com.desafio.dbserver.pauta.domain.Voto;
import com.desafio.dbserver.pauta.domain.VotoEnum;

public class VotoBuilder {

    public static Voto umVoto() {
        return Voto.builder()
                .cpf("10338927425")
                .idPauta(1L)
                .voto(VotoEnum.SIM)
                .build();
    }

    public static Voto umVoto(VotoEnum voto) {
        return Voto.builder()
                .cpf("10338927425")
                .idPauta(1L)
                .voto(voto)
                .build();
    }
}
