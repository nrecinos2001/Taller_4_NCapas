package com.nrecinos.preparcial.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nrecinos.preparcial.models.dtos.RegisterDTO;
import com.nrecinos.preparcial.models.entities.User;
import com.nrecinos.preparcial.services.AuthService;
import com.nrecinos.preparcial.services.UserService;
import com.nrecinos.preparcial.utils.RequestErrorHandler;

import jakarta.validation.Valid;
import net.bytebuddy.build.Plugin.Engine.ErrorHandler;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private RequestErrorHandler errorHandler;
	
	@Autowired 
	private AuthService authService;
	
	@Autowired 
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> singUp(@RequestBody @Valid RegisterDTO createUser, BindingResult validations){
		
		if(validations.hasErrors()){
			return new ResponseEntity<>(
					   errorHandler.mapErrors(validations.getFieldErrors()),
					   HttpStatus.BAD_REQUEST);
		}
		
		String username = createUser.getUsername();
		String email = createUser.getEmail();
		String password = createUser.getPassword();
		
		
		
	}
}
