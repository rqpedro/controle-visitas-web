package dto;

import entities.Perfil;
import entities.Usuario;

public class UsuarioDTO {
    private Long id;
    private String nomeUsuario;
    private String emailUsuario;
    private String login;
    private boolean ativo;
    private PerfilDTO perfil;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nomeUsuario, String emailUsuario, String login, boolean ativo, PerfilDTO perfil) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.login = login;
        this.ativo = ativo;
        this.perfil = perfil;
    }

    public UsuarioDTO(Usuario entity) {
        id = entity.getId();
        nomeUsuario = entity.getNomeUsuario();
        emailUsuario = entity.getEmailUsuario();
        login = entity.getLogin();
        ativo = entity.isAtivo();
        perfil = new PerfilDTO(entity.getPerfil());
    }

    public Long getId() {
        return id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public String getLogin() {
        return login;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public PerfilDTO getPerfil() {
        return perfil;
    }
}
