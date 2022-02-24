package org.generation.blogPessoal.DTO;

public class UsuarioLoginDTO { // CLASSE USADA SOMENTE COMO RESPOSTA PARA O CLIENT

    private String email;
    private String senha;
    private String tipoUsuario;
    private String foto;
    private String token;



    public UsuarioLoginDTO(String email, String senha, String tipoUsuario, String foto, String token) {
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.foto = foto;
        this.token = token;
    }


    public UsuarioLoginDTO() {
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

    public String getTipoUsuario() {
        return this.tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getFoto() {
        return this.foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
