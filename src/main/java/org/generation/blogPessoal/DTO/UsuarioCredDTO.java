package org.generation.blogPessoal.DTO;

public class UsuarioCredDTO {
    
    private long idUsuario;
    private String email;
    private String token;



    public UsuarioCredDTO(long idUsuario, String email, String token) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.token = token;
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

}
