package com.desafio.dbserver.pauta.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "ASSOCIADO")
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ASSOCIADO")
    private Long id;

    @Column(length = 500, nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;
}
