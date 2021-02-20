package com.oracle.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.oracle.models.Motif;

@Repository
@CrossOrigin
public interface MotifDao extends JpaRepository<Motif, Long> {
    //    List<Motif> findByUser(User user);
    public Motif findById(long id);
}
