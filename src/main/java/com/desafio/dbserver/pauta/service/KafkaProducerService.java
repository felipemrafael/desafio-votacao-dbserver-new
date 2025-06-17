package com.desafio.dbserver.pauta.service;

import org.springframework.stereotype.Service;

@Service
public interface KafkaProducerService {
    void writeMessage(String message);
}
