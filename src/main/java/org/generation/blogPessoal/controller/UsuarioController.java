package org.generation.blogPessoal.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.blogPessoal.DTO.UsuarioCredDTO;
import org.generation.blogPessoal.DTO.UsuarioDTO;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/usuarios")
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

	
	// Buscar todos os usuários
	@GetMapping("/todos")
	public ResponseEntity<List<Usuario>> findAllUsuario() {
		return usuarioService.getAllUsers();
	}
	
	// Buscar usuários por ID
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findUserByID(@PathVariable long id) {
		return usuarioService.findById(id);
	}
	
	
	// Buscar usuário por Nome
	@GetMapping("/pornome/{nome}")
	public ResponseEntity<List<UsuarioDTO>> findUserByName(@PathVariable String nome) {
		return usuarioService.getUserByName(nome);
	}
	
	// Editar dados do usuário
	@PutMapping("/edit")
	public ResponseEntity<Usuario> editUser(@Valid @RequestBody Usuario newUser) {
		return usuarioService.updateUser(newUser);
	}
	
	// Apagar usuário
	@SuppressWarnings("rawtypes") // "Esconde" alertas
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteUser(@PathVariable Long id) {
		return usuarioService.deleteUser(id);
	}
}
