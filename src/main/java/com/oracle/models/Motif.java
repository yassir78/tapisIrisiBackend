package com.oracle.models;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Motif {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String libelle;
    @OneToMany(mappedBy = "motif")
    private List<Propriete> proprietes;

    @OneToMany(mappedBy = "motif")
    private List<UserMotif> usermotifs;

    @JsonIgnore
    public List<UserMotif> getUsermotifs() {
        return usermotifs;
    }

    @JsonSetter
    public void setUsermotifs(List<UserMotif> usermotifs) {
        this.usermotifs = usermotifs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @JsonIgnore
    public List<Propriete> getProprietes() {
        return proprietes;
    }

    @JsonSetter
    public void setProprietes(List<Propriete> proprietes) {
        this.proprietes = proprietes;
    }
}
