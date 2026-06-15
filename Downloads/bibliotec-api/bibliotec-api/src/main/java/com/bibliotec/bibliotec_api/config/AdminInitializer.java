package com.bibliotec.bibliotec_api.config;

import com.bibliotec.bibliotec_api.model.Administrador;
import com.bibliotec.bibliotec_api.repository.AdministradorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    private final AdministradorRepository administradorRepository;

    public AdminInitializer(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Administrador adminExistente =
                administradorRepository.findByLogin("admin");

        if (adminExistente == null) {

            Administrador admin = new Administrador();

            admin.setLogin("admin");
            admin.setSenha("admin123");

            administradorRepository.save(admin);

            System.out.println("Administrador padrão criado com sucesso.");
        }
    }
}
