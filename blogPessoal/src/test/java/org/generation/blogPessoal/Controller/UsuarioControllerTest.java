package org.generation.blogPessoal.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.service.UsuarioService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {
    
    @Autowired
    private TestRestTemplate testRestTemplate;
    /*É um cliente para escrever testes criando um modelo de
    comunicação com as APIs HTTP. Ele fornece os mesmos
    métodos, cabeçalhos e outras construções do protocolo
    HTTP.*/

    @Autowired
    private UsuarioService usuarioService;

    
    
    @Test
    @DisplayName("Cadastrar Usuário")
    @Disabled
    public void mustSignUser() {
        HttpEntity<Usuario> request = new HttpEntity<Usuario>(new Usuario(0L, "Gustavo Oliveira", "gustavo@email.com", "123456", ""));
        ResponseEntity<Usuario> response = testRestTemplate
        .exchange("/usuarios/cadastro", HttpMethod.POST, request, Usuario.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(request.getBody().getNome(), response.getBody().getNome());
        assertEquals(request.getBody().getEmail(), response.getBody().getEmail());
    }


    @Test
    @DisplayName("Não pode cadastro duplicado")
    @Disabled
    public void mustNotSignDoubled() {

        usuarioService.cadastrarUsuario(new Usuario(0L, "Gustavo Oliveira", "gustavo@email.com", "123456", ""));

        HttpEntity<Usuario> request = new HttpEntity<Usuario>(new Usuario(0L, "Gustavo Oliveira", "gustavo@email.com", "123456", ""));
        ResponseEntity<Usuario> response = testRestTemplate
        .exchange("/usuarios/cadastro", HttpMethod.POST, request, Usuario.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    @DisplayName("Alterar dados do usuário")
    @Disabled
    public void mustEditUser() {
        Optional<Usuario> createUser = usuarioService.cadastrarUsuario(new Usuario(0L, "Gustavo Oliveira", "gustavo@email.com", "123456", ""));
        Usuario updateUser = new Usuario(createUser.get().getId(), "Gustavo Oliveira", "gustavo@email.com", "111222", "");
        HttpEntity<Usuario> request = new HttpEntity<Usuario>(updateUser);
        ResponseEntity<Usuario> response = testRestTemplate.withBasicAuth("gustavo@email.com", "123456")
        .exchange("/usuarios/edit", HttpMethod.PUT, request, Usuario.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updateUser.getSenha(), response.getBody().getSenha());
        }

    @Test
    @DisplayName("Listar todos os usuários")
    @Disabled
    public void mustShowAllUsers() {
        usuarioService.cadastrarUsuario(new Usuario(0L, "Gustavo Oliveira", "gustavo@email.com", "123456", ""));
        usuarioService.cadastrarUsuario(new Usuario(0L, "Thiago Oliveira", "thiago@email.com", "123456", ""));
        usuarioService.cadastrarUsuario(new Usuario(0L, "Jessica Olivera", "jessica@email.com", "123456", ""));

        @SuppressWarnings("rawtypes") // supressão de alertas
        ResponseEntity<List> response = testRestTemplate
        .withBasicAuth("thiago@email.com", "123456")
        .exchange("/usuarios/all", HttpMethod.GET, null, List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, response.getBody().size());
    }
}
