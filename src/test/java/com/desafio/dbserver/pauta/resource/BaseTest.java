package com.desafio.dbserver.pauta.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@AutoConfigureMockMvc
@EnableWebMvc
public class BaseTest {

    @Autowired
    protected MockMvc mockMvc;

}
