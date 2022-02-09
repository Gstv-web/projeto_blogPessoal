package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.DTO.PostagemDTO;
import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.generation.blogPessoal.service.PostagemService;
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
@RequestMapping("/postagem")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	
	@Autowired // injeção de dependências
	private PostagemRepository repository;

	@Autowired
	private PostagemService postagemService;
	
	@GetMapping("/todos") // get de todas as postagens
	public ResponseEntity<List<PostagemDTO>> findAllPostagens() {
		return postagemService.getAllPosts();
	}
	
	@GetMapping("/{id}")// Path variable aqui)                   // PathVariable informa que o parâmetro vai representar o caminho da url
	public ResponseEntity<Postagem> findPostByID(@PathVariable long id) {
		return repository.findById(id)                           // Retorna o método (que é optional)
				.map(resp -> ResponseEntity.ok(resp))            // E aí pode mostrar caso dê certo
				.orElse(ResponseEntity.notFound().build());      // ou mostrar erro caso não dê		
		//return ResponseEntity.status(200).body(repository.findById(id));
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<PostagemDTO>> findByTitle(@PathVariable String titulo) {
		return postagemService.getByTitle(titulo);
	}
	
	@PostMapping("/save") //request body vai pegar os dados inseridos na body e aí cria-se o objeto e um parâmetro
	public ResponseEntity<PostagemDTO> savePost(@RequestBody PostagemDTO newPost) {
		return postagemService.newPost(newPost);
	}
	
	@PutMapping("/edit") //request body vai pegar os dados inseridos na body e aí cria-se o objeto e um parâmetro
	public ResponseEntity<PostagemDTO> editPutPost(@RequestBody PostagemDTO editPost) {
		return postagemService.editPost(editPost);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletePost(@PathVariable long id) { // É void pois não irá retornar nada
		repository.deleteById(id);
	}
	
}
