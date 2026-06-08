
package com.bibliotec.bibliotec_api.repository;

import com.bibliotec.bibliotec_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
