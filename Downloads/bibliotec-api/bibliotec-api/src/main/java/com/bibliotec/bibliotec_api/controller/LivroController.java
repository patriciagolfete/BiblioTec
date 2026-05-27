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


@RestController
@RequestMapping("/livros")
public class LivroController {
    
    @Autowired
    private LivroRepository repository;
    
    @GetMapping
    public List<Livro> listar(){
        return repository.findAll();
    }
    
    @PostMapping
    public Livro Salvar(@RequestBody Livro livro){
        return repository.save(livro);
    }
    
    @GetMapping("/{id}")
    public Livro buscarPorId(@PathVariable Long id){
        return repository.findById(id).orElse(null);
    }
    
     @PutMapping("/{id}")
    public Livro alterar(@PathVariable Long id, @RequestBody Livro livro){
        livro.setId(id);
        return repository.save(livro);
    }
    
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id){
            repository.deleteById(id);
    }  
}
