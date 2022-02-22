package org.generation.blogPessoal.DTO;

public class UsuarioCredDTO {
    
    private long idUsuario;
    private String email;
    private String token;
    private String tipoUsuario;



    public UsuarioCredDTO(long idUsuario, String email, String token, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.token = token;
        this.tipoUsuario = tipoUsuario;
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

}
