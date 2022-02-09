package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.DTO.TemasDTO;
import org.generation.blogPessoal.repository.TemasRepository;
import org.generation.blogPessoal.service.TemasService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemasController {
	
	@Autowired
	private TemasRepository repository;

	@Autowired
	private TemasService temasService;
	
	@GetMapping("/all")
	public ResponseEntity<List<TemasDTO>> GetAll() {
		return temasService.getAllTags();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TemasDTO> getByID(@PathVariable long id) {
		return temasService.getTagById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<TemasDTO> newTag(@RequestBody TemasDTO tag) {
		return temasService.newTag(tag);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteTag(@PathVariable long id) { // É void pois não irá retornar nada
		repository.deleteById(id);
	}
}
