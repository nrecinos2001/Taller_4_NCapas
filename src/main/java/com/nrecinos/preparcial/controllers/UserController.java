package com.nrecinos.preparcial.controllers;


import java.rmi.ServerException;

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

import com.nrecinos.preparcial.models.dtos.AddSongToPlaylistDto;
import com.nrecinos.preparcial.models.dtos.CreatePlaylistDTO;
import com.nrecinos.preparcial.models.dtos.MessageDTO;
import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.Song;
import com.nrecinos.preparcial.models.entities.User;
import com.nrecinos.preparcial.repositories.PlaylistRepository;
import com.nrecinos.preparcial.repositories.UserRepository;
import com.nrecinos.preparcial.services.PlaylistService;
import com.nrecinos.preparcial.services.SongService;
import com.nrecinos.preparcial.services.UserService;
import com.nrecinos.preparcial.services.implementations.PlaylistServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
			
	
	
	}
