package com.nrecinos.preparcial.controllers;


import java.util.List;

import java.util.UUID;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nrecinos.preparcial.models.dtos.CreatePlaylistDTO;
import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.User;
import com.nrecinos.preparcial.repositories.PlaylistRepository;
import com.nrecinos.preparcial.repositories.UserRepository;
import com.nrecinos.preparcial.services.PlaylistService;
import com.nrecinos.preparcial.services.UserService;
import com.nrecinos.preparcial.services.implementations.PlaylistServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/playlist")

public class PlaylistController {
	
	@Autowired
	private PlaylistService playlistService;
	
    private final UserRepository userRepository;
	
	@Autowired
    public PlaylistController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@GetMapping("/{code}")
	public ResponseEntity<?> findPlaylistById(@PathVariable(name = "code") UUID code){
		Playlist playlist = playlistService.findPlaylistById(code);
		
		if(playlist == null) {
			return new ResponseEntity<>("playlist no encontrado", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(playlist, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<?> findAll(){
		List<Playlist> playlist = playlistService.findAll();
			
		if(playlist == null) {
			return new ResponseEntity<>("playlist vacio", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(playlist, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{code}")
	public ResponseEntity<?> deletePlaylistById(@PathVariable(name = "code") UUID code){
		playlistService.deletePlaylist(code);
		return new ResponseEntity<>("playlist eliminada", HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> savePlaylist(@ModelAttribute @Valid CreatePlaylistDTO info, BindingResult validations, @ModelAttribute @Valid User user){
		if(validations.hasErrors()){

			return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
		}
			playlistService.savePlaylist(info, user);
			return new ResponseEntity<>("Playlist created", HttpStatus.CREATED);
	}
	
	
	@GetMapping("/playlistssss")
	public ResponseEntity<?> getUserPlaylist(
			@RequestParam(value = "identifier") String identifier,
			@RequestParam(value = "fragment", required = false) String fragment
			){
		
		
		if(fragment != null) {
			List<Playlist> userPlaylist = playlistService.findByTitleContains(fragment);
			if(userPlaylist == null) {
				return new ResponseEntity<>("no se encontraron las playlist", HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<>(userPlaylist, HttpStatus.OK);
			}
		}else {
			List<Playlist> userPlaylist = playlistService.finByIdentifier(identifier);
			if(userPlaylist == null) {
				return new ResponseEntity<>("no se encontraron las playlist", HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<>(userPlaylist, HttpStatus.OK);
			}
		}
	}
}
