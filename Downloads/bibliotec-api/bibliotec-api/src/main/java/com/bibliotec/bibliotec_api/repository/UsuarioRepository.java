package com.bibliotec.bibliotec_api.repository;

import com.bibliotec.bibliotec_api.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Usuario findByEmail(String email);

    List<Usuario> findByNomeContainingIgnoreCase(String nome);
    
    List<Usuario> findAllByOrderByNomeAsc();
}