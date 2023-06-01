package com.nrecinos.preparcial.services.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrecinos.preparcial.models.dtos.CreatePlaylistDTO;
import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.User;
import com.nrecinos.preparcial.repositories.PlaylistRepository;
import com.nrecinos.preparcial.services.PlaylistService;
 
import jakarta.transaction.Transactional;

@Service
public class PlaylistServiceImp implements PlaylistService{

	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void savePlaylist(CreatePlaylistDTO playlist, User user) {
		// TODO Auto-generated method stub
		Playlist newPlaylist = new Playlist(
				playlist.getTitle(),
				playlist.getDescription(),
				user
				);
	}

	@Override
	public Playlist findPlaylistById(UUID code) {
		// TODO Auto-generated method stub
		return playlistRepository.findById(code).orElse(null);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deletePlaylist(UUID code) {
		// TODO Auto-generated method stub
		playlistRepository.deleteById(code);
		
	}

	@Override
	public List<Playlist> findAll() {
		// TODO Auto-generated method stub
		return playlistRepository.findAll();
	}

	@Override
	public List<Playlist> finByIdentifier(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Playlist> findByTitleContains(String identifier) {
		// TODO Auto-generated method stub
		return null;
	}
	


}
