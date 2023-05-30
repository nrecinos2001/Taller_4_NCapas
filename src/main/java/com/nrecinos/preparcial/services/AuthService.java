package com.nrecinos.preparcial.services;

import java.util.UUID;

import com.nrecinos.preparcial.models.dtos.LoginDTO;
import com.nrecinos.preparcial.models.dtos.RegisterDTO;

public interface AuthService {
		
	void singUp(RegisterDTO user);

}
		
