package org.generation.blogPessoal.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class UsuarioDTO {
    
    private long idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String foto;

    @JsonIgnoreProperties("fkUsuario")
    private List<PostagemDTO> postagem;


    // FAZER CONSTRUTOR E GETTERS AND SETTERS

    public UsuarioDTO(long idUsuario, String nome, String email, String senha, String foto, List<PostagemDTO> postagem) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
        this.postagem = postagem;
    }


    public UsuarioDTO() {}



    public long getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<PostagemDTO> getPostagem() {
        return this.postagem;
    }

    public void setPostagem(List<PostagemDTO> postagem) {
        this.postagem = postagem;
    }


}
