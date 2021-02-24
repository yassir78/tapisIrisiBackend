package com.oracle.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oracle.models.Motif;
import com.oracle.models.User;
import com.oracle.models.UserMotif;

public interface UserMotifDao extends JpaRepository<UserMotif, Long> {


    public List<UserMotif> findByUser(User user);

    public List<UserMotif> findByMotif(Motif motif);
 
    public List<UserMotif> findAll();

   
}
