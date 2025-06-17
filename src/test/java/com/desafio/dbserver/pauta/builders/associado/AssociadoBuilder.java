package com.desafio.dbserver.pauta.builders.associado;

import com.desafio.dbserver.pauta.domain.Associado;
import com.desafio.dbserver.pauta.domain.Pauta;
import com.desafio.dbserver.pauta.domain.VotoEnum;
import com.desafio.dbserver.pauta.web.rest.dto.AssociadoDTO;

import java.time.LocalDateTime;
import java.util.List;

import static com.desafio.dbserver.pauta.builders.voto.VotoBuilder.umVoto;
import static com.desafio.dbserver.pauta.utils.Constantes.ABERTA;
import static com.desafio.dbserver.pauta.utils.Constantes.FECHADA;
import static java.util.Set.of;

public class AssociadoBuilder {

    public static Associado umAssociado(){
        return Associado.builder()
                .id(1L)
                .nome("teste")
                .cpf("99999999999")
                .build();
    }

}
