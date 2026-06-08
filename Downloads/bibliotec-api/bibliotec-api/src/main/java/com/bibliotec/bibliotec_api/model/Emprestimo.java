package com.bibliotec.bibliotec_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String status;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Livro livro;
}