package com.nrecinos.preparcial.controllers;

import java.net.http.HttpRequest;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nrecinos.preparcial.models.entities.Song;
import com.nrecinos.preparcial.services.SongService;

@RestController
@RequestMapping("/songs")
public class SongController {
	@Autowired
	private SongService songService;
	
	@GetMapping("/songs")
	ResponseEntity<?>getAll(@RequestParam(defaultValue = "") String fragment) {
		List<Song> songs = songService.findAll(fragment);
		List<Song> songsFragment = songService.findAll(fragment);
		if (fragment.isEmpty()) {
			for (Song s : songs) {
				String slide = s.getTitle();
				if (slide.toUpperCase().contains(fragment.toUpperCase())) {
					songsFragment.add(s);
					
				}
			}
			
			return new ResponseEntity(
				songsFragment, HttpStatus.OK
			);
			
		}
		return new ResponseEntity<>(
				songs, HttpStatus.OK
			);

	}
}
