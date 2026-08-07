package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "visita")
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_visita")
    private LocalDate dataVisita;

    @ManyToOne
    @JoinColumn(name = "id_area")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_processo")
    private Processo processo;

    public Visita() {
    }

    public Visita(Long id, LocalDate dataVisita, Area area, Usuario usuario, Processo processo) {
        this.id = id;
        this.dataVisita = dataVisita;
        this.area = area;
        this.usuario = usuario;
        this.processo = processo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(LocalDate dataVisita) {
        this.dataVisita = dataVisita;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Visita visita = (Visita) o;
        return Objects.equals(id, visita.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Visita{" +
                "id=" + id +
                ", dataVisita=" + dataVisita +
                ", area=" + area +
                ", usuario=" + usuario +
                ", processo=" + processo +
                '}';
    }
}
