package com.desafio.dbserver.pauta.web.rest.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;

@Getter
public class ClientException extends RuntimeException {

    private final HttpStatusCode status;

    public ClientException(HttpStatusCode status, String message) {
        super(message);
        this.status = status;
    }

    public static ClientException from(ClientResponse response) {
        return new ClientException(response.statusCode(),
                response.toEntity(String.class).toString());
    }

    public static boolean isClientError(Throwable throwable) {
        return throwable instanceof ClientException
                && ((ClientException) throwable).isClientError();
    }

    public static boolean isServerError(Throwable throwable) {
        return throwable instanceof ClientException
                && ((ClientException) throwable).isServerError();
    }

    public boolean isClientError() {
        return status.is4xxClientError();
    }

    public boolean isServerError() {
        return status.is5xxServerError();
    }
}
