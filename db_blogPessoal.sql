CREATE DATABASE db_blogPessoal;
USE db_blogPessoal;

CREATE TABLE tb_temas(
	id BIGINT AUTO_INCREMENT,
    tags VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE tb_usuarios(
	id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(200) NOT NULL,
    usuario VARCHAR(200) NOT NULL,
    senha VARCHAR(200) NOT NULL,
    foto VARCHAR(200),
    PRIMARY KEY (id)
);

CREATE TABLE tb_postagens(
	id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    texto  VARCHAR(1000) NOT NULL,
    `data` DATE,
    fk_tema BIGINT,
    fk_usuario BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_tema) REFERENCES tb_temas (id),
    FOREIGN KEY (fk_usuario) REFERENCES tb_usuarios (id)
);
