package com.nrecinos.preparcial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {
		
	@Autowired
	private UserService userService;
	
	}
