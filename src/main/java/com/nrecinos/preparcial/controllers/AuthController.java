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

import com.nrecinos.preparcial.models.dtos.MessageDTO;
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
	
	@Autowired
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
		
		if (userService.findByUsernameOrEmail(username, email) != null) {
			return new ResponseEntity<>(
					new MessageDTO("Username or email already exist"),
					HttpStatus.BAD_REQUEST);
		}
		
		try {
			userService.save(createUser);
			return new ResponseEntity<>(
					new MessageDTO("User created"),
					HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new MessageDTO("Internal server error"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

}
