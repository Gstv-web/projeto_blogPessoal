package org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//	ENTIDADE - REPLICANDO A CONSTRUÇÃO DO BD

@Entity // Isto é uma entidade do JPA, uma tabela
@Table(name = "tb_postagens") // tabela de nome "postagem"
public class Postagem {
	
	@Id //Indica que é chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que é auto increment
	private long idPostagem;
	
	@NotNull
	@Size(max = 100)
	private String titulo;
	
	@NotNull
	@Size(max = 1000)
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP) // Vai mostrar a data
	private Date data;

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Temas fkTema;

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario fkUsuario;
	

	public long getIdPostagem() {
		return this.idPostagem;
	}

	public void setIdPostagem(long idPostagem) {
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

	public Temas getFkTema() {
		return this.fkTema;
	}

	public void setFkTema(Temas fkTema) {
		this.fkTema = fkTema;
	}

	public Usuario getFkUsuario() {
		return this.fkUsuario;
	}

	public void setFkUsuario(Usuario fkUsuario) {
		this.fkUsuario = fkUsuario;
	}

	
	
	
}
