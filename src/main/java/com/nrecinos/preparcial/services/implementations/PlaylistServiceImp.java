package com.nrecinos.preparcial.services.implementations;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrecinos.preparcial.models.dtos.CreatePlaylistDTO;
import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.Song;
import com.nrecinos.preparcial.models.entities.SongXPlaylist;
import com.nrecinos.preparcial.models.entities.User;
import com.nrecinos.preparcial.repositories.PlaylistRepository;
import com.nrecinos.preparcial.repositories.SongXPlaylistRepository;
import com.nrecinos.preparcial.services.PlaylistService;
import com.nrecinos.preparcial.services.SongService;

import jakarta.transaction.Transactional;

@Service
public class PlaylistServiceImp implements PlaylistService{

	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Autowired SongXPlaylistRepository songXPlaylistRepository;
	
	@Autowired 
	private SongService songService;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void saveSongPlaylist(UUID codep, UUID codes) {
		Song song = songService.findSongById(codes);
		Playlist playlist = findPlaylistById(codep);
		
		
		if(song == null || playlist == null) {
			return ;
		}
		 Date date = new Date();
		SongXPlaylist newSong = new SongXPlaylist(playlist, song);
		songXPlaylistRepository.save(newSong);
		
	}
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void savePlaylist(CreatePlaylistDTO playlist, User user) {
		// TODO Auto-generated method stub
		Playlist newPlaylist = new Playlist(
				playlist.getTitle(),
				playlist.getDescription(),
				user
				);
		playlistRepository.save(newPlaylist);
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

	@Override
	public boolean existsByUserAndTitle(User user, String title) {
		return playlistRepository.existsByUserAndTitle(user, title);
	}
	
	@Override
	public List<Playlist> findByUser(User user) {
		return playlistRepository.findByUser(user);
	}
	


}
