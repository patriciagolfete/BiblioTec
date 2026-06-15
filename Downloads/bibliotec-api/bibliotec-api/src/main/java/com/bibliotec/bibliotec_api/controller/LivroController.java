/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bibliotec.bibliotec_api.controller;

import com.bibliotec.bibliotec_api.model.Livro;
import com.bibliotec.bibliotec_api.repository.LivroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bibliotec.bibliotec_api.exception.RegraNegocioException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Sort;



@RestController
@RequestMapping("/livros")
public class LivroController {
    
    @Autowired
    private LivroRepository repository;
    
    @GetMapping
    public List<Livro> listar() {
        return repository.findAll(
            Sort.by(Sort.Direction.ASC, "titulo")
        );
    }
    
    @PostMapping
    public Livro salvar(@Valid @RequestBody Livro livro){
        return repository.save(livro);
    }
    
    @GetMapping("/{id}")
    public Livro buscarPorId(@PathVariable Long id){
    return repository.findById(id)
        .orElseThrow(() ->
            new RegraNegocioException(
                "Livro não encontrado."
                ));    
    }
    
     @PutMapping("/{id}")
    public Livro alterar(@PathVariable Long id, @Valid @RequestBody Livro livro){
        livro.setId(id);
        return repository.save(livro);
    }
    
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id){
        if (!repository.existsById(id)) {
            throw new RegraNegocioException(
                "Livro não encontrado."
            );
        }
        
        repository.deleteById(id);
    }
    
    @GetMapping("/ordenar/autor")
    public List<Livro> listarPorAutor() {
        return repository.findAllByOrderByAutorAsc();
}

    @GetMapping("/ordenar/editora")
    public List<Livro> listarPorEditora() {
        return repository.findAllByOrderByEditoraAsc();
    }
    
    @GetMapping("/disponiveis")
    public List<Livro> listarDisponiveis() {
        return repository.findByDisponivelOrderByTituloAsc(true);
    }

    @GetMapping("/emprestados")
    public List<Livro> listarEmprestados() {
        return repository.findByDisponivel(false);
    }
    
    @GetMapping("/buscar/titulo")
    public List<Livro> buscarPorTitulo(@RequestParam String titulo) {

        List<Livro> livros = repository.findByTituloContainingIgnoreCase(titulo);

        if (livros.isEmpty()) {
            throw new RegraNegocioException("Livro não encontrado.");
        }

        return livros;
    }

    @GetMapping("/buscar/autor")
    public List<Livro> buscarPorAutor(@RequestParam String autor) {

        List<Livro> livros = repository.findByAutorContainingIgnoreCase(autor);

        if (livros.isEmpty()) {
            throw new RegraNegocioException("Autor não encontrado.");
        }

        return livros;
    }

    @GetMapping("/buscar/editora")
    public List<Livro> buscarPorEditora(@RequestParam String editora) {

        List<Livro> livros = repository.findByEditoraContainingIgnoreCase(editora);

        if (livros.isEmpty()) {
            throw new RegraNegocioException("Editora não encontrada.");
        }

        return livros;
    }
}
