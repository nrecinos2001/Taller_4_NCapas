package com.nrecinos.preparcial.services.implementations;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.nrecinos.preparcial.models.dtos.RegisterDTO;
import com.nrecinos.preparcial.models.dtos.UpdatePasswordDTO;
import com.nrecinos.preparcial.models.entities.User;
import com.nrecinos.preparcial.repository.UserRepository;
import com.nrecinos.preparcial.services.UserService;

import jakarta.transaction.Transactional;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void register(RegisterDTO user) {
		User newUser = new User(
				user.getUsername(),
				user.getPassword(),
				user.getEmail()
				);
		
		userRepository.save(newUser);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void save(RegisterDTO user) throws Exception {
		User newUser = new User(
				user.getUsername(),
				user.getPassword(),
				user.getEmail()
				);
		
		userRepository.save(newUser);
	}
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteById(UUID code) throws Exception {
		userRepository.deleteById(code);
	}

	@Override
	public User findOneById(UUID code) {
		return userRepository.findById(code).orElse(null);
	}

	@Override
	public void updatePassword(UpdatePasswordDTO password, User user) {
		User modifiedPassword = user;
		modifiedPassword.setPassword(password.getNewPassword());
		userRepository.save(modifiedPassword);
		
	}

	@Override
	public User findOneByIdentificator(User user) {
		return userRepository.findOneByIdentificator(user.getEmail());
	}
	

}
