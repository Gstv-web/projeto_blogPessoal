package org.generation.blogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_temas")
public class Temas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTema;
	
	@NotNull
	@Size(min = 2, max = 50)
	private String tags;

	@OneToMany(mappedBy = "fkTema", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("fkTema")
	private List<Postagem> postagem;

	

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

	public List<Postagem> getPostagem() {
		return this.postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
	
	
	
}
