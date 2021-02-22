package com.oracle.response;

import com.oracle.models.Motif;
import com.oracle.models.User;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;

public class UserMotifResponse {
    private long id;
    private String name;
    private String type;
    private Motif motif;
    private String fileDownloadUri;


    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    private User user;
//    UserMotifResponse(userMotif.getName(), fileDownloadUri, userMotif.getType(), userMotif.getImage().length);

    public UserMotifResponse(String name,String fileDownloadUri,String type) {
        this.name = name;
        this.type = type;
        this.fileDownloadUri = fileDownloadUri;
    }

    public UserMotifResponse() {
    }

    public UserMotifResponse(long id, String name, String type, Motif motif, User user) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.motif = motif;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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



    public Motif getMotif() {
        return motif;
    }

    public void setMotif(Motif motif) {
        this.motif = motif;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
