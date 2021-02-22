package com.oracle.response;

import com.oracle.models.Motif;
import com.oracle.models.User;

public class UserMotifResponse {
    private long id;
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

    public UserMotifResponse(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public UserMotifResponse() {
    }

    public UserMotifResponse(long id,Motif motif, User user) {
        this.id = id;
        this.motif = motif;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
