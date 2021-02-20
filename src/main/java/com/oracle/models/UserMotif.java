package com.oracle.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ConfigurationProperties(prefix = "file")
public class UserMotif {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserMotif(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.image = data;
    }

    private String type;
    @Lob
    private byte[] image;
    @ManyToOne
    private Motif motif;
    @ManyToOne
    private User user;

    @JsonIgnore
    public User getUser() {
        return user;
    }

    @JsonSetter
    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Motif getMotif() {
        return motif;
    }

    public void setMotif(Motif motif) {
        this.motif = motif;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public UserMotif(byte[] image) {
        this.image = image;
    }
}
