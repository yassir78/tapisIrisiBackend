package com.oracle.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.UserDao;
import com.oracle.models.User;
import com.oracle.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findById(long id) {
		return userDao.findById(id);
	}

	@Override
	public User register(User user) {
		return userDao.save(user);
	}

}
