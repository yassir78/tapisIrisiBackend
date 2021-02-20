package com.oracle.dao;

import com.oracle.models.Motif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.oracle.models.User;

import java.util.List;

@Repository
@CrossOrigin
public interface UserDao extends JpaRepository<User, Long> {
<<<<<<< HEAD
   public User findByLogin(String login);
   public User findByLoginAndPassword(String email,String password);
=======
    public User findById(long id);
>>>>>>> f088134903207cd2006d96642cfbc3c103daf98b
}
