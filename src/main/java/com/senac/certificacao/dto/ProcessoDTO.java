package com.senac.certificacao.dto;

import com.senac.certificacao.entities.Processo;
import jakarta.validation.constraints.NotBlank;

public class ProcessoDTO {

    private Long id;
    @NotBlank(message = "Campo obrigatório")
    private String nomeProcesso;

    public ProcessoDTO() {
    }

    public ProcessoDTO(Long id, String nomeProcesso) {
        this.id = id;
        this.nomeProcesso = nomeProcesso;
    }

    public ProcessoDTO(Processo entity) {
        id = entity.getId();
        nomeProcesso = entity.getNomeProcesso();
    }

    public Long getId() {
        return id;
    }

    public String getNomeProcesso() {
        return nomeProcesso;
    }
}
