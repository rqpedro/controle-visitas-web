package com.senac.certificacao.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_area")
    private String nomeArea;

    @OneToMany(mappedBy = "area")
    private List<Visita> visitaList = new ArrayList<>();

    public Area() {
    }

    public Area(Long id, String nomeArea) {
        this.id = id;
        this.nomeArea = nomeArea;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArea() {
        return nomeArea;
    }

    public void setNomeArea(String nomeArea) {
        this.nomeArea = nomeArea;
    }

    public List<Visita> getVisitaList() {
        return visitaList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return Objects.equals(id, area.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", nomeArea='" + nomeArea + '\'' +
                '}';
    }
}
