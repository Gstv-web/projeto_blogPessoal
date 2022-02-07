package org.generation.blogPessoal.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PostagemDTO {
    
    private String idPostagem;
    private String titulo;
    private String texto;
    private Date data = new java.sql.Timestamp(System.currentTimeMillis());

    @JsonIgnoreProperties("postagem")
    private TemasDTO fkTema;

    @JsonIgnoreProperties("postagem")
    private UsuarioDTO fkUsuario;

 

    public PostagemDTO(String idPostagem, String titulo, String texto, Date data, TemasDTO fkTema, UsuarioDTO fkUsuario) {
        this.idPostagem = idPostagem;
        this.titulo = titulo;
        this.texto = texto;
        this.data = data;
        this.fkTema = fkTema;
        this.fkUsuario = fkUsuario;
    }


    public PostagemDTO() {}



    public String getIdPostagem() {
        return this.idPostagem;
    }

    public void setIdPostagem(String idPostagem) {
        this.idPostagem = idPostagem;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public TemasDTO getFkTema() {
        return this.fkTema;
    }

    public void setFkTema(TemasDTO fkTema) {
        this.fkTema = fkTema;
    }

    public UsuarioDTO getFkUsuario() {
        return this.fkUsuario;
    }

    public void setFkUsuario(UsuarioDTO fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

}
