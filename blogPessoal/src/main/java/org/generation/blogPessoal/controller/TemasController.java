package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Temas;
import org.generation.blogPessoal.repository.TemasRepository;
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
@RequestMapping("/api/v1/temas")
public class TemasController {
	
	@Autowired
	private TemasRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Temas>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Temas> getByID(@PathVariable long id) {
		return repository.findById(id)                           
				.map(resp -> ResponseEntity.ok(resp))            
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/save")
	public ResponseEntity<Temas> newTag(@RequestBody Temas newTag) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(newTag));
	}
	
	@PutMapping("/edit")
	public ResponseEntity<Temas> editTag(@RequestBody Temas newTag) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(newTag));
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteTag(@PathVariable long id) { // É void pois não irá retornar nada
		repository.deleteById(id);
	}
}
