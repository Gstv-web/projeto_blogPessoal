package org.generation.blogPessoal.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
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
    @Order(1)
    public void mustSignUser() {
        HttpEntity<Usuario> request = new HttpEntity<Usuario>(new Usuario(1, "Gustavo Oliveira", "gustavo@email.com", "123456", ""));
        ResponseEntity<Usuario> response = testRestTemplate
        .exchange("/usuarios/cadastro", HttpMethod.POST, request, Usuario.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(request.getBody().getNome(), response.getBody().getNome());
        assertEquals(request.getBody().getEmail(), response.getBody().getEmail());
    }


    @Test
    @DisplayName("Não pode cadastro duplicado")
    @Order(2)
    public void mustNotSignDoubled() {

        usuarioService.cadastrarUsuario(new Usuario(0L, "Gustavo Oliveira", "gustavo@email.com", "123456", ""));

        HttpEntity<Usuario> request = new HttpEntity<Usuario>(new Usuario(0L, "Gustavo Oliveira", "gustavo@email.com", "123456", ""));
        ResponseEntity<Usuario> response = testRestTemplate
        .exchange("/usuarios/cadastro", HttpMethod.POST, request, Usuario.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

    }
}
