package com.nrecinos.preparcial.services;

import java.util.UUID;

import com.nrecinos.preparcial.models.dtos.LoginDTO;

public interface AuthService {
		
	void login(LoginDTO login);

}
		
