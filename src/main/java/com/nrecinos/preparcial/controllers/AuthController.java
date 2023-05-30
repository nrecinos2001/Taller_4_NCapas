package com.nrecinos.preparcial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nrecinos.preparcial.models.dtos.RegisterDTO;
import com.nrecinos.preparcial.models.entities.User;
import com.nrecinos.preparcial.services.AuthService;
import com.nrecinos.preparcial.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired 
	private AuthService authService;
	
	@Autowired 
	private UserService userService;
	
	@PostMapping("/singup")
	public ResponseEntity<?> singUp(@ModelAttribute @Valid RegisterDTO user, BindingResult validations){
		User users = userService.findOneByIdentificator(user.getEmail());
		if(validations.hasErrors() && (users==null)) {
			return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
		}
		else {
			authService.singUp(user);
			return new ResponseEntity<>("User Created", HttpStatus.CREATED);
		}
	}
}
