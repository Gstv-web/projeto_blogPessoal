package org.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.generation.blogPessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository repository;
	
	// Logar
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Authentication(@RequestBody Optional<UserLogin> user) {
		return usuarioService.logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	// Cadastrar
	@PostMapping("/cadastro")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario) {
		return usuarioService.cadastrarUsuario(usuario).map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
		.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	// Editar dados do usuário
	@PutMapping("/edit")
	public ResponseEntity<Usuario> editUser(@RequestBody Usuario newUser) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(newUser));
	}

	// Buscar todos os usuários
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> findAllUsuario() {
		List<Usuario> list = repository.findAll();
		if (list.isEmpty()) {		
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}
	}
	

	// Buscar usuários por ID
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findUserByID(@PathVariable long id) {
		return repository.findById(id)
				.map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
				.orElse(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
	}
	
	// Salvar novo usuário
	@PostMapping("/save")
	public ResponseEntity<Usuario> saveUser(@RequestBody Usuario newUser) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(newUser));
	}
	
	
	// Apagar usuário
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable long id) { // É void pois não irá retornar nada
		repository.deleteById(id);
	}
}
