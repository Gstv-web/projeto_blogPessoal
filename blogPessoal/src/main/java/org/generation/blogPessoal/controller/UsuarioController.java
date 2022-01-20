package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	
	// Buscar todos os usuários
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> findAllUsuario() {
		return ResponseEntity.status(200).body(repository.findAll());
	}
	
	// Buscar usuários por ID
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findUserByID(@PathVariable long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	// Salvar novo usuário
	@PostMapping("/save")
	public ResponseEntity<Usuario> saveUser(@RequestBody Usuario newUser) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(newUser));
	}
	
	// Editar dados do usuário
	@PutMapping("/edit")
	public ResponseEntity<Usuario> editUser(@RequestBody Usuario newUser) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(newUser));
	}
	
	// Apagar usuário
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable long id) { // É void pois não irá retornar nada
		repository.deleteById(id);
	}
}
