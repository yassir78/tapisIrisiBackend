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
    public User findById(long id);
}
