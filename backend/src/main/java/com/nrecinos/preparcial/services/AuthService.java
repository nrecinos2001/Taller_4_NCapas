package com.nrecinos.preparcial.services;

import java.util.UUID;

import com.nrecinos.preparcial.models.dtos.LoginDTO;
import com.nrecinos.preparcial.models.dtos.RegisterDTO;
import com.nrecinos.preparcial.models.entities.User;

public interface AuthService {
	void singUp(RegisterDTO user);
	User signIn(String identificator, String password);
	Boolean comparePassword(String toCompare, String current);
}
		
