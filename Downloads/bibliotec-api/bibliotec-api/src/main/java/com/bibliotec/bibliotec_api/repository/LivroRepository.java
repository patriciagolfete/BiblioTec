
package com.bibliotec.bibliotec_api.repository;

import com.bibliotec.bibliotec_api.model.Livro;
import jakarta.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LivroRepository extends JpaRepository<Livro, Long> {

    public Livro findById(SingularAttribute<AbstractPersistable, Serializable> id);
    
}
