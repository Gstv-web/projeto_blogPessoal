package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Temas;
import org.generation.blogPessoal.repository.TemasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/save")
	public ResponseEntity<Temas> newTag(@RequestBody Temas newTag) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(newTag));
	}
	
	
}
