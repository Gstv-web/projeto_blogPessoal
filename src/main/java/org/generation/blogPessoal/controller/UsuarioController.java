package org.generation.blogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.blogPessoal.DTO.UsuarioCredDTO;
import org.generation.blogPessoal.DTO.UsuarioLoginDTO;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	// Logar
	@PostMapping("/logar")
	public ResponseEntity<UsuarioCredDTO> pegarCredencial (@Valid @RequestBody UsuarioLoginDTO dto) {
		return usuarioService.logar(dto);
	}

	// Cadastrar
	@PostMapping("/cadastro")
	public ResponseEntity<Usuario> registrar(@Valid @RequestBody Usuario novoUsuario) {
		return usuarioService.cadastrarUsuario(novoUsuario);
	}

	// Editar dados do usuário
	@PutMapping("/edit")
	public ResponseEntity<Usuario> editUser(@Valid @RequestBody Usuario newUser) {
		return usuarioService.updateUser(newUser);
	}

	// Buscar todos os usuários
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> findAllUsuario() {
		return usuarioService.getAllUsers();
	}
	

	// Buscar usuários por ID
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findUserByID(@PathVariable long id) {
		return usuarioService.findById(id);
	}
	
	
	// Apagar usuário
	@SuppressWarnings("rawtypes") // "Esconde" alertas
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteUser(@PathVariable Long id) {
		return usuarioService.deleteUser(id);
	}
}
