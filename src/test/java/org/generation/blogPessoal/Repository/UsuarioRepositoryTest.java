package org.generation.blogPessoal.Repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    void start() {

        usuarioRepository.save(new Usuario(0L, "Gustavo Oliveira", "gustavo@email.com", "123456", ""));
        usuarioRepository.save(new Usuario(0L, "Karol Oliveira", "karol@email.com", "123456", ""));
        usuarioRepository.save(new Usuario(0L, "Catarina Oliveira", "catarina@email.com", "123456", ""));
        usuarioRepository.save(new Usuario(0L, "Paulo Oliveira", "paulo@email.com", "123456", ""));
    }

    @Test
    @DisplayName("Retorna 1 usuário")
    @Disabled
    public void mustReturOneUser() {
        Optional<Usuario> usuario = usuarioRepository.findByEmail("karol@email.com");
        assertTrue(usuario.get().getEmail().equals("karol@email.com"));
    }

    @Test
    @DisplayName("Retorna 4 usuários")
    public void listaDeUsuarios() {
        List<Usuario> usuario = usuarioRepository.findAllByNomeContainingIgnoreCase("Oliveira");
        assertEquals(4, usuario.size());
        assertTrue(usuario.get(0).getNome().equals("Gustavo Oliveira"));
        assertTrue(usuario.get(1).getNome().equals("Karol Oliveira"));
        assertTrue(usuario.get(2).getNome().equals("Catarina Oliveira"));
        assertTrue(usuario.get(3).getNome().equals("Paulo Oliveira"));
    }
}
