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
<<<<<<< HEAD
		// TODO Auto-generated method stub
		User foundUser = userDao.findByLogin(user.getLogin());
		if(foundUser != null) {
			return new User();
		}else {
			return userDao.save(user);
		}
		
	}

	@Override
	public User login(String login, String password) {
		// TODO Auto-generated method stub
		User user = userDao.findByLoginAndPassword(login, password);
		if(user == null) {
			return new User();
		}else {
			return user;
		}
=======
		return userDao.save(user);
>>>>>>> f088134903207cd2006d96642cfbc3c103daf98b
	}

}
