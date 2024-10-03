package lt.ca.javau10.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.ca.javau10.entities.UserEntity;
import lt.ca.javau10.services.UserService;

@RestController
public class UsersController {

	Logger logger = LogManager.getLogger(UsersController.class);

	UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	public UsersController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping("/admin")
	public List<UserEntity> admin() {
		return userService.getAll();
	}
	
	@GetMapping("/mypass")
	public String getMyPass() {
		return passwordEncoder.encode("pass");
	}
	
	
	
	
	
}
