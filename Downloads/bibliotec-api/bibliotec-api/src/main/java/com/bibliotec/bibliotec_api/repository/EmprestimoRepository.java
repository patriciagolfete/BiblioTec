package com.bibliotec.bibliotec_api.repository;

import com.bibliotec.bibliotec_api.model.Emprestimo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByStatus(String status);
}