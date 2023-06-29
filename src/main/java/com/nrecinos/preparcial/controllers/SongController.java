package com.nrecinos.preparcial.controllers;

import java.net.http.HttpRequest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nrecinos.preparcial.models.entities.Song;
import com.nrecinos.preparcial.services.SongService;

@RestController
@RequestMapping("/songs")
@CrossOrigin(origins = "*")
public class SongController {
	@Autowired
	private SongService songService;
	
<<<<<<< HEAD
	@SuppressWarnings("unchecked")
	@GetMapping("/")
	ResponseEntity<?>getAll(@RequestParam(defaultValue = " ")String fragment, @RequestParam(defaultValue = "0") Int page, @RequestParam(defaultValue = "10") Int size) {
=======
	/*@SuppressWarnings("unchecked")*/
	@GetMapping("")
	ResponseEntity<?>getAll(@RequestParam(defaultValue = "", name="fragment") String fragment) {
>>>>>>> 3262c578253b4b42946094d77b2941636cba1a18
		
		Page<Song> songs = songService.findAll(fragment, page, size);
		return new ResponseEntity<>(
			songs, HttpStatus.OK
		);
	}
}
