package com.nrecinos.preparcial.services;

import java.util.List;
import java.util.UUID;

import com.nrecinos.preparcial.models.dtos.RegisterDTO;
import com.nrecinos.preparcial.models.dtos.UpdatePasswordDTO;
import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.Token;
import com.nrecinos.preparcial.models.entities.User;

public interface UserService {
	void save(RegisterDTO user) throws Exception;
	void deleteById(UUID code) throws Exception;
	User findOneById(UUID code);
	void updatePassword(UpdatePasswordDTO password, User user);
	User findByUsernameOrEmail(String username, String email);
	Token registerToken(User user) throws Exception;
	Boolean isTokenValid(User user, String token);
	void cleanTokens(User user) throws Exception;
	//Find User authenticated
		User findUserAuthenticated();
}
