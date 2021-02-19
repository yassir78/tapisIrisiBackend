package com.oracle.service;

import java.util.List;


import com.oracle.models.Motif;
import com.oracle.models.User;

import org.springframework.web.multipart.MultipartFile;

public interface MotifService {

    public List<Motif> findAll();

//    public List<Motif> findByUser(User user);
//    List<Motif> findByUser(User user);


    public Motif save(Motif motif);

    public Motif findById(long id);



//	publlic Motif findByPicture()

}
