package lt.ca.javau10.configurations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lt.ca.javau10.services.UserService;


@Configuration
public class SecurityConfig {
	
	Logger logger = LogManager.getLogger(SecurityConfig.class);

	UserService userService;
	
	
	public SecurityConfig(UserService userService) {
		this.userService = userService;
	}
	
	//Spring Security 6.3.3
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/admin/*").hasRole("ADMIN")
	            .anyRequest().permitAll()
	        )
	        .formLogin(form -> form	           
	            .permitAll() // Permit all access to the login page
	        )
	        .logout(logout -> logout
	            .permitAll() // Allow all to access the logout endpoint
	        ).csrf(csrf -> csrf.disable());

	    return http.build();
	}
	
    @Bean
    UserDetailsService userDetailsService() {

        return username -> userService.getUserByUsername(username)
            .map(user -> User.withUsername(user.getUserName())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))  // Aiškiai nurodome, kad tai String[] masyvas
                    .build())
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
  
	
}
