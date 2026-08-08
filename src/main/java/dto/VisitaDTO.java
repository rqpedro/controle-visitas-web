package dto;

import entities.Visita;

import java.time.LocalDate;

public class VisitaDTO {
    private Long id;
    private LocalDate dataVisita;
    private AreaDTO area;
    private UsuarioDTO usuario;
    private ProcessoDTO processo;

    public VisitaDTO() {
    }

    public VisitaDTO(Long id, LocalDate dataVisita, AreaDTO area, UsuarioDTO usuario, ProcessoDTO processo) {
        this.id = id;
        this.dataVisita = dataVisita;
        this.area = area;
        this.usuario = usuario;
        this.processo = processo;
    }

    public VisitaDTO(Visita entity) {
        id = entity.getId();
        dataVisita = entity.getDataVisita();
        area = new AreaDTO(entity.getArea());
        usuario = new UsuarioDTO(entity.getUsuario());
        processo = new ProcessoDTO(entity.getProcesso());
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDataVisita() {
        return dataVisita;
    }

    public AreaDTO getArea() {
        return area;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public ProcessoDTO getProcesso() {
        return processo;
    }
}
