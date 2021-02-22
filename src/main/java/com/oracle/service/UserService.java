package com.oracle.service;

import com.oracle.models.Role;
import java.util.List;

import com.oracle.models.User;

public interface UserService {

    public List<User> findAll();

    public User findById(long id);

    public User register(User user);

    public User login(String login, String password);

    public User findByRole(Role role);
}
