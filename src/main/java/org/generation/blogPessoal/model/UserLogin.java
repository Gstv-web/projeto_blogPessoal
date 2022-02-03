package org.generation.blogPessoal.model;

public class UserLogin { // CLASSE USADA SOMENTE COMO RESPOSTA PARA O CLIENT
 
    private String nome;

    private String email;

    private String senha;
    
    private String token;
    
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
