package com.nrecinos.preparcial.services;

import java.util.UUID;

import com.nrecinos.preparcial.models.dtos.RegisterDTO;
import com.nrecinos.preparcial.models.dtos.UpdatePasswordDTO;
import com.nrecinos.preparcial.models.entities.User;

public interface UserService {
	void register(RegisterDTO user);
	void save(RegisterDTO user) throws Exception;
	void deleteById(UUID code) throws Exception;
	User findOneById(UUID code);
	void updatePassword(UpdatePasswordDTO password, User user);
}
