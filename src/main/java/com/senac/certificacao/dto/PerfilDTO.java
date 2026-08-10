package com.senac.certificacao.dto;


import com.senac.certificacao.entities.Perfil;
import jakarta.validation.constraints.NotBlank;

public class PerfilDTO {

    private Long id;
    @NotBlank(message = "Campo obrigatório")
    private String nomePerfil;

    public PerfilDTO() {
    }

    public PerfilDTO(Long id, String nomePerfil) {
        this.id = id;
        this.nomePerfil = nomePerfil;
    }

    public PerfilDTO(Perfil entity) {
        id = entity.getId();
        nomePerfil = entity.getNomePerfil();
    }

    public Long getId() {
        return id;
    }

    public String getNomePerfil() {
        return nomePerfil;
    }
}
