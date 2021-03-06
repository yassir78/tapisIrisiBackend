package com.oracle.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.models.User;
import com.oracle.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/tapis-irisi/user")
public class UserRest {
	@Autowired
	private UserService userService;
	@PutMapping("/update")
	public User update(@RequestBody User user) {
		return userService.update(user);
	}

	@GetMapping("/login/{login}/password/{password}")
     public User login(@PathVariable String login,@PathVariable String password) {
		return userService.login(login, password);
	}

	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return userService.register(user);
	}

	@GetMapping("/")
	public List<User> findAll() {
		return userService.findAll();
	}


}
