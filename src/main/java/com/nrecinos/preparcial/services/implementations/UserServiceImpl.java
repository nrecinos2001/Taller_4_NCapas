package com.nrecinos.preparcial.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.nrecinos.preparcial.models.dtos.RegisterDTO;
import com.nrecinos.preparcial.models.dtos.UpdatePasswordDTO;
import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.User;
import com.nrecinos.preparcial.repository.UserRepository;
import com.nrecinos.preparcial.services.UserService;

import jakarta.transaction.Transactional;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	

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
	public List<Playlist> getUserPlaylist(String identifier) {
		return userRepository.getPlaylist(identifier);
	}
	
	@Override
	public List<Playlist> getUserPlaylistTitle(String identifier, String fragment){
		return userRepository.getPlaylistTitle(identifier, fragment);
	}
	

	@Override
	public User findOneByIdentificator(String user) {
		return userRepository.findOneByIdentificator(user);
	}
	

}
