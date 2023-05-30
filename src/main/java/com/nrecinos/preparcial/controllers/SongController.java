package com.nrecinos.preparcial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nrecinos.preparcial.models.entities.Song;
import com.nrecinos.preparcial.services.SongService;

@Controller
@RequestMapping(value = "/songs")
public class SongController {
	@Autowired
	private SongService songService;
	
	@GetMapping("/")
	List<Song> getAll() {
		List<Song> songs = songService.findAll();
		return songs;
	}
}
