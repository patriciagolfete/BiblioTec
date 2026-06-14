
package com.bibliotec.bibliotec_api.controller;

import com.bibliotec.bibliotec_api.model.Usuario;
import com.bibliotec.bibliotec_api.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bibliotec.bibliotec_api.exception.RegraNegocioException;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
 @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public List<Usuario> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Usuario salvar(@Valid @RequestBody Usuario usuario) {
        if (repository.findByEmail(usuario.getEmail()) != null) {
        throw new RegraNegocioException(
            "Já existe um usuário com este e-mail."
        );
    }

    return repository.save(usuario);
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() ->
                    new RegraNegocioException(
                            "Usuário não encontrado."
                    ));
    }

    @PutMapping("/{id}")
    public Usuario alterar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        usuario.setId(id);
        return repository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new RegraNegocioException(
                "Usuário não encontrado."
            );
        }
        repository.deleteById(id);
    }
    
    @GetMapping("/buscar")
    public List<Usuario> buscarPorNome(@RequestParam String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
}
}
