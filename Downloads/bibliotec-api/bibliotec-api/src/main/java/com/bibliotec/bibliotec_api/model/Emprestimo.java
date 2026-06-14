package com.bibliotec.bibliotec_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Data do empréstimo é obrigatória.")
    private LocalDate dataEmprestimo;
    
    private LocalDate dataDevolucao;
    private String status;

    @ManyToOne
    @NotNull(message = "Usuário é obrigatório.")
    private Usuario usuario;

    @ManyToOne
    @NotNull(message = "Livro é obrigatório.")
    private Livro livro;
}