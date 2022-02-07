package org.generation.blogPessoal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.generation.blogPessoal.DTO.PostagemDTO;
import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostagemService {
    
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PostagemRepository repository;


    // Encotrar postagens antes de qualquer coisa
    public ResponseEntity<List<PostagemDTO>> postsEncontrados(List<Postagem> postagens) {
        if (postagens.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK)
            .body(postagens.stream()
            .map(post -> mapper.map(post, PostagemDTO.class))
            .collect(Collectors.toList()));
        }
    }
    

    public ResponseEntity<List<PostagemDTO>> getAllPosts() {
        List<Postagem> getAllPosts = repository.findAll();
        return postsEncontrados(getAllPosts);
    }

    public ResponseEntity<List<PostagemDTO>> getByTitle(String titulo) {
        List<Postagem> postByTitle = repository.findAllByTituloContainingIgnoreCase(titulo);
        return postsEncontrados(postByTitle);
    }

    public ResponseEntity<PostagemDTO> getPostById(Long id) {
        return repository.findById(id)
                            .map(resp -> ResponseEntity.ok(mapper.map(resp, PostagemDTO.class)))
                            .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    public ResponseEntity<PostagemDTO> newPost(PostagemDTO post) {
        Postagem novo = repository.save(mapper.map(post, Postagem.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(novo, PostagemDTO.class));
    }

    public ResponseEntity<PostagemDTO> editPost(PostagemDTO edit) {
        Postagem edited = repository.save(mapper.map(edit, Postagem.class));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(edited, PostagemDTO.class));
    }

    public void deletePost(Long id) {
        repository.deleteById(id);
    }

}
