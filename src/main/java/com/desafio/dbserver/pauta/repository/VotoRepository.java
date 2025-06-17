package com.desafio.dbserver.pauta.repository;

import com.desafio.dbserver.pauta.domain.Voto;
import com.desafio.dbserver.pauta.domain.VotoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, VotoPK> {
}
