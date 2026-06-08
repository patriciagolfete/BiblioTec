package com.bibliotec.bibliotec_api.repository;

import com.bibliotec.bibliotec_api.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    Administrador findByLoginAndSenha(String login, String senha);
    Administrador findByLogin(String login);
}