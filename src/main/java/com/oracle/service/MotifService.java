package com.oracle.service;

import java.util.List;

import com.oracle.models.Motif;
import com.oracle.models.User;

import org.springframework.web.multipart.MultipartFile;

public interface MotifService {

    public List<Motif> findAll();

    //    public List<Motif> findByUser(User user);
//    List<Motif> findByUser(User user);

    public void delete(long id);


    public Motif save(Motif motif);

    public Motif updateMotif(Motif motif, long id);

    public Motif findById(long id);


    public List<Motif> findByImage(byte[] image);

//	publlic Motif findByPicture()

}
