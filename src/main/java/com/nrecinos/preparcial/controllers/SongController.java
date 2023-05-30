package com.nrecinos.preparcial.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nrecinos.preparcial.models.dtos.CreateSongDTO;
import com.nrecinos.preparcial.models.entities.Song;
import com.nrecinos.preparcial.services.SongService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/song")
public class SongController {
	
	@Autowired
	private SongService songService;
	
	
	@PostMapping("/create")
	public ResponseEntity<?> saveSong(@ModelAttribute @Valid CreateSongDTO song, BindingResult validations) throws Exception{
		if(validations.hasErrors()) {
			
			return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
		}
		
		
			songService.saveSong(song);
			return new ResponseEntity<>("Playlist created", HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{code}")
	public ResponseEntity<?> findSongById(@PathVariable(name ="code") UUID code){
		Song song = songService.findSongById(code);
		if(song == null) {
			return new ResponseEntity<>("cancion no existe", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(song, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{code}")
	public ResponseEntity<?> deleteSongById(@PathVariable(name = "code") UUID code) throws Exception{
		Song song = songService.findSongById(code);
				
				if(song == null) {
					return new ResponseEntity<>("La Cancion no existe", HttpStatus.NO_CONTENT);
				}
				songService.deleteSongById(code);
				return new ResponseEntity<>("Cancion Eliminada", HttpStatus.OK);
		
	}
	
	@PatchMapping("/update/{code}")
	public ResponseEntity<?> updateSongTitle(@PathVariable(name = "code") UUID code){
		
	}
	
}
