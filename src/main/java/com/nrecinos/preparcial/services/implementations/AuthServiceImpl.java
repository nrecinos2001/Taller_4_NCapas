package com.nrecinos.preparcial.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrecinos.preparcial.models.dtos.RegisterDTO;
import com.nrecinos.preparcial.models.entities.User;
import com.nrecinos.preparcial.repository.UserRepository;
import com.nrecinos.preparcial.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private UserRepository userRepository ;
	@Override
	public void singUp(RegisterDTO user) {
		User newUser = new User(
				user.getUsername(),
				user.getPassword(),
				user.getEmail()
				);
		
		userRepository.save(newUser);
	}
	
}
