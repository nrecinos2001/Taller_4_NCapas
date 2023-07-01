package com.nrecinos.preparcial.controllers;


import java.io.IOException;
import java.rmi.ServerException;

import java.util.List;

import java.util.UUID;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.nrecinos.preparcial.models.dtos.PlaylistWithSongsDTO;
import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.Song;
import com.nrecinos.preparcial.models.entities.User;
import com.nrecinos.preparcial.repositories.PlaylistRepository;
import com.nrecinos.preparcial.repositories.UserRepository;
import com.nrecinos.preparcial.services.PlaylistService;
import com.nrecinos.preparcial.services.SongService;
import com.nrecinos.preparcial.services.UserService;
import com.nrecinos.preparcial.services.implementations.JWTTokenFilter;
import com.nrecinos.preparcial.services.implementations.PlaylistServiceImp;
import com.nrecinos.preparcial.utils.JWTTools;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/playlists")
@CrossOrigin("*")
public class PlaylistController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlaylistService playlistService;
	
	@Autowired
    private final UserRepository userRepository;
    
    @Autowired
    private SongService songService;
    
    @Autowired
    private JWTTools jwtTools;
	
    public PlaylistController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	
	
	@PostMapping("/{code}")
	public ResponseEntity<?> saveSongPlaylist(@PathVariable(name = "code") UUID playlistCode, @RequestBody @Valid AddSongToPlaylistDto addSongToPlaylistDto, BindingResult validations){
		if(validations.hasErrors()) {
			return new ResponseEntity<>(validations.getFieldError(), HttpStatus.BAD_REQUEST);
		}
		UUID songCode = UUID.fromString(addSongToPlaylistDto.getSongCode());
		Playlist playlist = playlistService.findPlaylistById(playlistCode);
		if(playlist == null) {
			return new ResponseEntity<>("Playlist Not Found", HttpStatus.BAD_REQUEST);
		}
		Song song = songService.findSongById(songCode);
		if(song == null) {
			return new ResponseEntity<>("Song Not Found", HttpStatus.BAD_REQUEST);
		}
		try {
			playlistService.saveSongPlaylist(playlistCode, songCode);
			return new ResponseEntity<>("Song add to Playlist", HttpStatus.CREATED);
		} catch (ServerException e) {
			return new ResponseEntity<>("Song is already in playlist", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	@GetMapping("/{code}")
	public ResponseEntity<?> findPlaylistById(@PathVariable(name = "code") UUID code){
		PlaylistWithSongsDTO playlist = playlistService.findPlaylistWithSongById(code);
		
		if(playlist == null) {
			return new ResponseEntity<>("Playlist Not Found", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(playlist, HttpStatus.OK);
		
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> findByUser(@RequestParam(defaultValue = "", name = "fragment") String fragment, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, HttpServletRequest request) {
		String token = jwtTools.extractTokenFromRequest(request);
		String identificator = jwtTools.getUsernameFrom(token);
		User user = userRepository.findOneByUsername(identificator);;
		if(user == null) {
			return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
		}else {			
			Page<Playlist> playlistsByUser = playlistService.findByUser(user, fragment, page, size);
			return new ResponseEntity<>(playlistsByUser, HttpStatus.OK);
		}
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<?> findAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size){
		
		Page<Playlist> playlist = playlistService.findAll(page, size);
			
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
	public ResponseEntity<?> savePlaylist(@RequestBody @Valid CreatePlaylistDTO info, BindingResult validations, HttpServletRequest request){

		if(validations.hasErrors()){		
			return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
		}
		
		String token = jwtTools.extractTokenFromRequest(request);
		String identificator = jwtTools.getUsernameFrom(token);
		
		User user = userService.findByUsernameOrEmail(identificator, identificator);
		if(user == null) {
			return new ResponseEntity<>(
					new MessageDTO("El usuario no existe"),
					HttpStatus.NOT_FOUND);
		}
		boolean data = playlistService.existsByUserAndTitle(user, info.getTitle());
		if (data) {
			return new ResponseEntity<>(
					new MessageDTO("La playlist ya existe"),
					HttpStatus.CONFLICT);
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
