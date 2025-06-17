package com.desafio.dbserver.pauta.domain;

import com.desafio.dbserver.pauta.web.rest.dto.SessaoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDateTime;
import java.util.Set;

import static com.desafio.dbserver.pauta.utils.Constantes.ABERTA;
import static com.desafio.dbserver.pauta.utils.Constantes.FECHADA;
import static com.desafio.dbserver.pauta.utils.Util.estaNuloOuVazio;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PAUTA")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_PAUTA")
    private Long id;

    @Column(name = "TITULO")
    private String titulo;

    @OneToMany
    @LazyCollection(value = LazyCollectionOption.FALSE)
    @JoinColumn(name = "ID_PAUTA", referencedColumnName = "ID_PAUTA")
    Set<Voto> votos;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DTH_LIMITE")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime tempoLimite;

    public boolean naoFoiEnviada() {
        return !this.enviadoKafka;
    }

    public boolean isEnviadoKafka() {
        return enviadoKafka;
    }

    public void setEnviadoKafka(boolean enviadoKafka) {
        this.enviadoKafka = enviadoKafka;
    }

    @Column(columnDefinition = "boolean default false")
    private boolean enviadoKafka;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Set<Voto> getVotos() {
        return votos;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTempoLimite() {
        return tempoLimite;
    }

    public LocalDateTime abrirVotacao(SessaoDTO sessaoDTO) {
        this.status = "ABERTA";
        this.enviadoKafka = false;
        return this.tempoLimite = obterTempoFinal(sessaoDTO);
    }

    private LocalDateTime obterTempoFinal(SessaoDTO sessaoDTO) {
        if (estaNuloOuVazio(sessaoDTO.minutos())) {
            return LocalDateTime.now().plusMinutes(1);
        } else {
            return LocalDateTime.now().plusMinutes(sessaoDTO.minutos());
        }
    }

    public boolean estahFechada() {
        if (naoEstahAberta() || venceuTempoLimite()) {
            this.status = FECHADA;
            return true;
        } else {
            return false;
        }
    }

    public boolean estahFechadaIhNaoFoiEnviada() {
        return estahFechada() && naoFoiEnviada();
    }

    private boolean naoEstahAberta() {
        return !this.status.equals(ABERTA);
    }

    private boolean venceuTempoLimite() {
        LocalDateTime agora = LocalDateTime.now();
        return agora.isAfter(tempoLimite);
    }

    public void obterStatusFechadaCasoNulo(Pauta pauta) {
        if (estaNuloOuVazio(pauta.getStatus())) {
            this.status = FECHADA;
        }
    }

    @Override
    public String toString() {
        return "Pauta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", votos=" + votos +
                ", status='" + status + '\'' +
                ", tempoLimite=" + tempoLimite +
                ", enviadoKafka=" + enviadoKafka +
                '}';
    }
}
