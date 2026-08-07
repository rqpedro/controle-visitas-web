package entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_perfil")
    private String nomePerfil;

    @OneToMany(mappedBy = "perfil")
    private List<Usuario> usuarioList = new ArrayList<>();

    public Perfil() {
    }

    public Perfil(Long id, String nomePerfil) {
        this.id = id;
        this.nomePerfil = nomePerfil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePerfil() {
        return nomePerfil;
    }

    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Perfil perfil = (Perfil) o;
        return Objects.equals(id, perfil.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "id=" + id +
                ", nomePerfil='" + nomePerfil + '\'' +
                '}';
    }
}
