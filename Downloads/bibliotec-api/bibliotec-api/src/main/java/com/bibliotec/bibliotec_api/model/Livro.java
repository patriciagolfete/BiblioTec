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
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título é obrigatório.")
    @Size(max = 150,
          message = "Título deve ter no máximo 150 caracteres.")
    private String titulo;

    @NotBlank(message = "Autor é obrigatório.")
    @Size(max = 100,
          message = "Autor deve ter no máximo 100 caracteres.")
    private String autor;

    @NotBlank(message = "Editora é obrigatória.")
    @Size(max = 100,
          message = "Editora deve ter no máximo 100 caracteres.")
    private String editora;

    private Boolean disponivel;
}