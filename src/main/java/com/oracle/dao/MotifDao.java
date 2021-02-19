package com.oracle.dao;

import com.oracle.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.oracle.models.Motif;

import java.util.List;

@Repository
@CrossOrigin
public interface MotifDao extends JpaRepository<Motif, Long> {
    //    List<Motif> findByUser(User user);
    public Motif findById(long id);
}
