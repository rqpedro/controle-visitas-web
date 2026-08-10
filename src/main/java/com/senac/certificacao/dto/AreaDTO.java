package com.senac.certificacao.dto;

import com.senac.certificacao.entities.Area;
import jakarta.validation.constraints.NotBlank;

public class AreaDTO {

    private Long id;
    @NotBlank(message = "Campo Obrigatório")
    private String nomeArea;

    public AreaDTO() {
    }

    public AreaDTO(Long id, String nomeArea) {
        this.id = id;
        this.nomeArea = nomeArea;
    }

    public AreaDTO(Area entity) {
        id = entity.getId();
        nomeArea = entity.getNomeArea();
    }

    public Long getId() {
        return id;
    }

    public String getNomeArea() {
        return nomeArea;
    }
}
