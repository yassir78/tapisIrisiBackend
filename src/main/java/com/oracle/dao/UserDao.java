package com.oracle.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.oracle.models.User;

@Repository
@CrossOrigin
public interface UserDao extends JpaRepository<User, Long> {
   public User findByLogin(String login);
   public User findByLoginAndPassword(String email,String password);
    public User findById(long id);
}
