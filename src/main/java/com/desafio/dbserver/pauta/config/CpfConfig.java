package com.desafio.dbserver.pauta.config;

import com.desafio.dbserver.pauta.service.validator.CpfServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class CpfConfig {

    @Bean
    public CpfServiceClient cpfServiceClient(@Value("${cpf.api.base-url}") String cpfApiBaseUrl) {
        WebClient client = WebClient.builder().baseUrl(cpfApiBaseUrl).build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client)).build();
        return factory.createClient(CpfServiceClient.class);
    }
}
