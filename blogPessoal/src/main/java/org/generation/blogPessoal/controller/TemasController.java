package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Temas;
import org.generation.blogPessoal.repository.TemasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temas")
@CrossOrigin("*")
public class TemasController {
	
	@Autowired
	private TemasRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Temas>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
}
