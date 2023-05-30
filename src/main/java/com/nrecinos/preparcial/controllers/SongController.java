package com.nrecinos.preparcial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nrecinos.preparcial.models.entities.Song;
import com.nrecinos.preparcial.services.SongService;

@Controller
@RequestMapping(value = "/songs")
public class SongController {
	@Autowired
	private SongService songService;
	
	@GetMapping("/")
	List<Song> getAll(@RequestParam("fragment") String fragment) {
		System.out.println(fragment);
		List<Song> songs = songService.findAll(fragment);
		return songs;
	}
}
