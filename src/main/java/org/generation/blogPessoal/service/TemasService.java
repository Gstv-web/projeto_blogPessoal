package org.generation.blogPessoal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.generation.blogPessoal.DTO.TemasDTO;
import org.generation.blogPessoal.model.Temas;
import org.generation.blogPessoal.repository.TemasRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TemasService {
    
    @Autowired
    private TemasRepository repository;

    @Autowired
    private ModelMapper mapper;


    public ResponseEntity<List<TemasDTO>> tagsEncontradas(List<Temas> tags) {
        if (tags.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK)
            .body(tags.stream()
            .map(tag -> mapper.map(tag, TemasDTO.class))
            .collect(Collectors.toList()));
        }
    }

    public ResponseEntity<List<TemasDTO>> getAllTags() {
        List<Temas> getAllTags = repository.findAll();
        return tagsEncontradas(getAllTags);
    }

    public ResponseEntity<TemasDTO> getTagById(Long id) {
        return repository.findById(id)
                            .map(resp -> ResponseEntity.ok(mapper.map(resp, TemasDTO.class)))
                            .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    public ResponseEntity<List<TemasDTO>> getByTag(String tag) {
        List<Temas> byTag = repository.findAllByTagsContainingIgnoreCase(tag);
        return tagsEncontradas(byTag);
    }

    public ResponseEntity<TemasDTO> newTag(TemasDTO newtag) {
        Temas novo = repository.save(mapper.map(newtag, Temas.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(novo, TemasDTO.class));
    }

    public void deleteTag(Long id) {
        repository.deleteById(id);
    }
}
