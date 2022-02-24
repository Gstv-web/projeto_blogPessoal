package org.generation.blogPessoal.DTO;

public class UsuarioCredDTO {
    
    private long idUsuario;
    private String nome;
    private String email;
    private String token;
    private String foto;
    private String tipoUsuario;



    public UsuarioCredDTO(long idUsuario, String nome,String token, String email, String tipoUsuario, String foto) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.token = token;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
        this.foto = foto;
    }


    public UsuarioCredDTO() {}


    public long getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
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


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
