package entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "processo")
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_processo")
    private String nomeProcesso;

    @OneToMany(mappedBy = "processo")
    private List<Visita> visitaList = new ArrayList<>();

    public Processo() {
    }

    public Processo(Long id, String nomeProcesso) {
        this.id = id;
        this.nomeProcesso = nomeProcesso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProcesso() {
        return nomeProcesso;
    }

    public void setNomeProcesso(String nomeProcesso) {
        this.nomeProcesso = nomeProcesso;
    }

    public List<Visita> getVisitaList() {
        return visitaList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Processo processo = (Processo) o;
        return Objects.equals(id, processo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Processo{" +
                "id=" + id +
                ", nomeProcesso='" + nomeProcesso + '\'' +
                '}';
    }
}
