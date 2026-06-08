package com.bibliotec.bibliotec_api.repository;

import com.bibliotec.bibliotec_api.model.Livro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findAllByOrderByAutorAsc();

    List<Livro> findAllByOrderByEditoraAsc();
}