package org.generation.blogPessoal.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class TemasDTO {
    
    private long idTema;
    private String tags;

    @JsonIgnoreProperties("fkTema")
    private List<PostagemDTO> postagem;



    public TemasDTO(long idTema, String tags, List<PostagemDTO> postagem) {
        this.idTema = idTema;
        this.tags = tags;
        this.postagem = postagem;
    }



    public TemasDTO() {}



    public long getIdTema() {
        return this.idTema;
    }

    public void setIdTema(long idTema) {
        this.idTema = idTema;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<PostagemDTO> getPostagem() {
        return this.postagem;
    }

    public void setPostagem(List<PostagemDTO> postagem) {
        this.postagem = postagem;
    }


}
