package com.oracle.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@ConfigurationProperties(prefix = "file")
public class UserMotif {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Lob
	private byte[] image;
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Motif motif;
	@ManyToOne
	private User user;
	@Transient
	private String fileUrl;
    
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Motif getMotif() {
		return motif;
	}

	public void setMotif(Motif motif) {
		this.motif = motif;
	}

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



	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public UserMotif(byte[] image) {
		this.image = image;
	}

	public UserMotif() {
		// TODO Auto-generated constructor stub
	}
}
