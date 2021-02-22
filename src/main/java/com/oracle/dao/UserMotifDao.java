package com.oracle.dao;

import com.oracle.models.Motif;
import com.oracle.models.User;
import com.oracle.models.UserMotif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserMotifDao extends JpaRepository<UserMotif, Long> {


    public List<UserMotif> findByUser(User user);

    public List<UserMotif> findByMotif(Motif motif);

    public List<UserMotif> findAll();

   
}
