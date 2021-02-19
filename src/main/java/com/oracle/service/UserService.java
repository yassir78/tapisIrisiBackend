package com.oracle.service;

import java.util.List;

import com.oracle.models.User;

public interface UserService {

	public List<User> findAll();
	public User findById(long id);

	public User register(User user);
}
