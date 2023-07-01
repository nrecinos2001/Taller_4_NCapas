package com.nrecinos.preparcial.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nrecinos.preparcial.models.dtos.RegisterDTO;
import com.nrecinos.preparcial.models.entities.User;
import com.nrecinos.preparcial.repositories.UserRepository;
import com.nrecinos.preparcial.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void singUp(RegisterDTO user) {
		User newUser = new User(
				user.getUsername(),
				user.getPassword(),
				user.getEmail()
				);
		
		userRepository.save(newUser);
	}
	@Override
	public User signIn(String identificator, String password) {
		User user = userRepository.findByUsernameOrEmail(identificator, identificator);
		Boolean passwordMatches = this.comparePassword(password, user.getPassword());
		if (passwordMatches == true) {
			return user;			
		}
		return null;
	}
	@Override
	public Boolean comparePassword(String toCompare, String current) {
		return passwordEncoder.matches(toCompare, current);
	}
}
