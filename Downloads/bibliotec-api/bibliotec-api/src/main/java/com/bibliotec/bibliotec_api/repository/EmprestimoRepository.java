package com.bibliotec.bibliotec_api.repository;

import com.bibliotec.bibliotec_api.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

}