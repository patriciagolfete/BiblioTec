package com.bibliotec.bibliotec_api.controller;

import com.bibliotec.bibliotec_api.model.Emprestimo;
import com.bibliotec.bibliotec_api.model.Livro;
import com.bibliotec.bibliotec_api.model.Usuario;
import com.bibliotec.bibliotec_api.repository.EmprestimoRepository;
import com.bibliotec.bibliotec_api.repository.LivroRepository;
import com.bibliotec.bibliotec_api.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository repository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Emprestimo> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Emprestimo salvar(@RequestBody Emprestimo emprestimo) {
        Long livroId = emprestimo.getLivro().getId();
        Long usuarioId = emprestimo.getUsuario().getId();

        Livro livro = livroRepository.findById(livroId).orElse(null);
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if (livro == null || usuario == null) {
            return null;
        }

        if (livro.getDisponivel() == false) {
            return null;
        }

        livro.setDisponivel(false);

        if (usuario.getQntEmprestimos() == null) {
            usuario.setQntEmprestimos(0);
        }

        usuario.setQntEmprestimos(usuario.getQntEmprestimos() + 1);

        livroRepository.save(livro);
        usuarioRepository.save(usuario);

        emprestimo.setLivro(livro);
        emprestimo.setUsuario(usuario);
        emprestimo.setStatus("EM_ABERTO");

        return repository.save(emprestimo);
    }

    @GetMapping("/{id}")
    public Emprestimo buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Emprestimo alterar(@PathVariable Long id, @RequestBody Emprestimo emprestimo) {
        emprestimo.setId(id);
        return repository.save(emprestimo);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
}