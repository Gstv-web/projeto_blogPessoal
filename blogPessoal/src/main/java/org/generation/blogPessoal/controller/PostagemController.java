package org.generation.blogPessoal.controller;

import java.util.List;


import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
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
@RequestMapping("api/v1/postagem")
public class PostagemController {
	
	@Autowired // injeção de dependências
	private PostagemRepository repository;
	
	@GetMapping("/all") // get de todas as postagens
	public ResponseEntity<List<Postagem>> findAllPostagens() {
		return ResponseEntity.status(200).body(repository.findAll()); // status(código status) body(o que vai aparecer no corpo)
	}
	
	@GetMapping("/{id}")// Path variable aqui)                   // PathVariable informa que o parâmetro vai representar o caminho da url
	public ResponseEntity<Postagem> findPostByID(@PathVariable long id) {
		return repository.findById(id)                           // Retorna o método (que é optional)
				.map(resp -> ResponseEntity.ok(resp))            // E aí pode mostrar caso dê certo
				.orElse(ResponseEntity.notFound().build());      // ou mostrar erro caso não dê		
		//return ResponseEntity.status(200).body(repository.findById(id));
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> findByTitle(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping("/save") //request body vai pegar os dados inseridos na body e aí cria-se o objeto e um parâmetro
	public ResponseEntity<Postagem> savePost(@RequestBody Postagem newPost) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(newPost));
	}
	
	@PutMapping("/edit") //request body vai pegar os dados inseridos na body e aí cria-se o objeto e um parâmetro
	public ResponseEntity<Postagem> editPutPost(@RequestBody Postagem newPost) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(newPost));
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletePost(@PathVariable long id) { // É void pois não irá retornar nada
		repository.deleteById(id);
	}
	
}
