package lt.ca.javau10.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.ca.javau10.services.UserService;

@RestController
public class HomeController {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	Logger logger = LogManager.getLogger(HomeController.class);
	
	@GetMapping("/")
	public String home() {
		return "Welcome to the home page";
	}
	
	
	@GetMapping("/public")
	public String publicEndpoint() {	
		
		return "Welcome, guest, you are in the public page";
	}
	
}
