package com.oracle.models;


import java.util.List;


import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import javax.persistence.Transient;

@Entity
public class Motif {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String libelle;

    private String description;
    @Transient
    private double pourcentage;
    @OneToMany(mappedBy = "motif", cascade = CascadeType.REMOVE)
    private List<Propriete> proprietes;

    @OneToMany(mappedBy = "motif", cascade = CascadeType.REMOVE)
    private List<UserMotif> usermotifs;

    public Motif(String libelle2, String desc) {
		// TODO Auto-generated constructor stub
    	this.libelle = libelle2;
    	this.description = desc;
	}

	public Motif() {
		// TODO Auto-generated constructor stub
	}

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

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
    
    public List<Propriete> getProprietes() {
        return proprietes;
    }

    @JsonSetter
    public void setProprietes(List<Propriete> proprietes) {
        this.proprietes = proprietes;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }
    
    
}
