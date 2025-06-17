package com.desafio.dbserver.pauta.schedule;

import com.desafio.dbserver.pauta.domain.Pauta;
import com.desafio.dbserver.pauta.service.KafkaProducerService;
import com.desafio.dbserver.pauta.service.PautaService;
import com.desafio.dbserver.pauta.service.ResultadoService;
import com.desafio.dbserver.pauta.web.rest.dto.ResultadoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.desafio.dbserver.pauta.utils.Constantes.FECHADA;
import static com.desafio.dbserver.pauta.utils.JsonUtil.toJson;

@Component
@EnableScheduling
@Slf4j
@AllArgsConstructor
public class PautaSchedule {

    private final PautaService pautaService;
    private final ResultadoService resultadoService;
    private final KafkaProducerService kafkaProducerService;

    //Fecha de 5 em 5 minutos as pautas que estão abertas e não foram enviadas
    @Scheduled(fixedRate = 300000)
    public void fecharPautaCasoVerdadeiro() {
        pautaService.consultarPautasAbertas().stream()
                .filter(Pauta::estahFechadaIhNaoFoiEnviada).forEach(pauta -> {
            ResultadoDTO resultadoDTO = resultadoService.obterResultado(pauta.getId());
            atulizarResultado(resultadoDTO);
            log.info("Enviando resultado : {}", resultadoDTO);
            kafkaProducerService.writeMessage(toJson(resultadoDTO));

            log.info("salvando pauta fechada : {}", pauta);
            sinalizarEnvioPauta(pauta);
            pautaService.atualizarPauta(pauta);
        });
    }

    private void atulizarResultado(ResultadoDTO resultadoDTO) {
        resultadoDTO = new ResultadoDTO(resultadoDTO.seqPauta(), resultadoDTO.titulo(),
                FECHADA, resultadoDTO.quantidadeSim(), resultadoDTO.quantidadeNao(), resultadoDTO.resultado());
    }

    private void sinalizarEnvioPauta(Pauta pauta) {
        pauta.setEnviadoKafka(true);
    }
}
