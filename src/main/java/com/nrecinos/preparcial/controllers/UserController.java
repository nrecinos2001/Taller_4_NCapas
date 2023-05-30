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

	
	@GetMapping("/playlist")
	public ResponseEntity<?> getUserPlaylist(
			@RequestParam(value = "identifier") String identifier,
			@RequestParam(value = "fragment", required = false) String fragment
			){
		
		
		if(fragment != null) {
			List<Playlist> userPlaylist = userService.getUserPlaylistTitle(identifier, fragment);
			if(userPlaylist == null) {
				return new ResponseEntity<>("no se encontraron las playlist", HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<>(userPlaylist, HttpStatus.OK);
			}
		}else {
			List<Playlist> userPlaylist = userService.getUserPlaylist(identifier);
			if(userPlaylist == null) {
				return new ResponseEntity<>("no se encontraron las playlist", HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<>(userPlaylist, HttpStatus.OK);
			}
		}
			
		
		
	}
	
}
