package com.oracle.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.UserDao;
import com.oracle.models.Role;
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

	}

    @Override
    public User findByRole(Role role) {
        return userDao.findByRole(role);
    }

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		User searchUser = userDao.findByLogin(user.getLogin());
		searchUser.setNom(user.getNom());
		searchUser.setPrenom(user.getPrenom());
		searchUser.setPassword(user.getPassword());
		User updatedUser = userDao.save(searchUser);
		return updatedUser;
	}

}
