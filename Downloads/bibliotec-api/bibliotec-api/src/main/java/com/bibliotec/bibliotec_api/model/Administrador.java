package com.bibliotec.bibliotec_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Getter
@Setter
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Login é obrigatório.")
    @Size(min = 3, max = 30,
          message = "Login deve possuir entre 3 e 30 caracteres.")
    private String login;

    @NotBlank(message = "Senha é obrigatória.")
    @Size(min = 4,
          message = "Senha deve possuir pelo menos 4 caracteres.")
    private String senha;
}