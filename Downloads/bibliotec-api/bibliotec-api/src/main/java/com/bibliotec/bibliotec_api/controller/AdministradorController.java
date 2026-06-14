package com.bibliotec.bibliotec_api.controller;

import com.bibliotec.bibliotec_api.model.Administrador;
import com.bibliotec.bibliotec_api.repository.AdministradorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bibliotec.bibliotec_api.exception.RegraNegocioException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorRepository repository;

    @GetMapping
    public List<Administrador> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Administrador salvar(@Valid @RequestBody Administrador administrador){
        if (repository.findByLogin(administrador.getLogin()) != null) {
            return null;
        }
        
        return repository.save(administrador);
    }

    @PostMapping("/login")
    public Administrador login(@Valid @RequestBody Administrador administrador) {

        Administrador adm =
                repository.findByLoginAndSenha(
                        administrador.getLogin(),
                        administrador.getSenha());

        if (adm == null) {
            throw new RegraNegocioException(
                    "Usuário ou senha inválidos."
            );
        }

        return adm;
    }
}