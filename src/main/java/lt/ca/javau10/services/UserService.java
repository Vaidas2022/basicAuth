package lt.ca.javau10.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lt.ca.javau10.entities.UserEntity;
import lt.ca.javau10.repositories.UserRepository;

@Service
public class UserService {

	Logger logger = LogManager.getLogger(UserService.class);

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder ) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;		
	}
	
	public UserEntity createUser(String username, String password, Set<String> roles) {
		String encodedPassword = passwordEncoder.encode(password);
		UserEntity user = new UserEntity(username, encodedPassword, roles );
		return userRepository.save(user);
	}
	
	public UserEntity createUser(UserEntity user) {
		user.setPassword( passwordEncoder.encode(user.getPassword()) );
		return userRepository.save(user);
	}
	
	public Optional<UserEntity> getUserByUsername(String username){
		return userRepository.findByUserName(username);
	}

	public List<UserEntity> getAll() {
		
		return userRepository.findAll();
	}
	
	
}
