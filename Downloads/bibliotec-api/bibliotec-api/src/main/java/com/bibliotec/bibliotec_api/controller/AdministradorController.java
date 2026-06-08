package com.bibliotec.bibliotec_api.controller;

import com.bibliotec.bibliotec_api.model.Administrador;
import com.bibliotec.bibliotec_api.repository.AdministradorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Administrador salvar(@RequestBody Administrador administrador){
        if (repository.findByLogin(administrador.getLogin()) != null) {
            return null;
        }
        
        return repository.save(administrador);
    }

    @PostMapping("/login")
    public Administrador login(@RequestBody Administrador administrador) {
        return repository.findByLoginAndSenha(
                administrador.getLogin(),
                administrador.getSenha()
        );
    }
}