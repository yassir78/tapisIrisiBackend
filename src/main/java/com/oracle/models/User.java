package com.oracle.models;

import java.sql.Blob;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Blob profile_image;
    @OneToMany(mappedBy = "user")
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Blob getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(Blob profile_image) {
        this.profile_image = profile_image;
    }

}
