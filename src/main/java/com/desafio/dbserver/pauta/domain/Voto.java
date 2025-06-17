package com.desafio.dbserver.pauta.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter

@IdClass(VotoPK.class)
@Entity
@Table(name = "VOTO", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID_ASSOCIADO", "ID_PAUTA"})})
public class Voto {


    @Id
    @Column(name = "ID_PAUTA")
    private Long idPauta;

    @Id
    @Column(name = "ID_ASSOCIADO")
    private Long idAssociado;

    @Column(name = "CPF")
    @JsonIgnore
    private String cpf;

    @Column(name = "VOTO")
    @Enumerated(EnumType.STRING)
    @JdbcType(value = PostgreSQLEnumJdbcType.class)
    private VotoEnum voto;


    @Override
    public String toString() {
        return "Voto{" +
                "idPauta=" + idPauta +
                ", idAssociado=" + idAssociado +
                ", cpf='" + cpf + '\'' +
                ", voto='" + voto + '\'' +
                '}';
    }
}
