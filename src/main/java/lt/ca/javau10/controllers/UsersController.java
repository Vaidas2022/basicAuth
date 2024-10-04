package lt.ca.javau10.controllers;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.ca.javau10.entities.UserEntity;
import lt.ca.javau10.services.UserService;

@RestController
@RequestMapping("/admin")
public class UsersController {

	Logger logger = LogManager.getLogger(UsersController.class);

	UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	public UsersController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping("/all")
	public List<UserEntity> admin() {
		return userService.getAll();
	}
	
	@PostMapping("/add")
	public UserEntity addUser(@RequestBody UserEntity user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/dummy")
	public UserEntity getDummy() {
		return new UserEntity("username", "password", Set.of("ADMIN", "USER"));
	}
	
	
	
	
	
}
