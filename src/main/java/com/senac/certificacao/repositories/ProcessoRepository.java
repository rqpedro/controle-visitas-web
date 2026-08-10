package com.senac.certificacao.repositories;

import com.senac.certificacao.entities.Processo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {
}
