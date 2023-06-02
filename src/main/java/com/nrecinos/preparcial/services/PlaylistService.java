package com.nrecinos.preparcial.services;

import java.util.List;
import java.util.UUID;

import com.nrecinos.preparcial.models.dtos.CreatePlaylistDTO;
import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.User;

import jakarta.validation.Valid;

public interface PlaylistService {
	void savePlaylist(CreatePlaylistDTO playlist, User user);
	Playlist findPlaylistById(UUID code);
	void deletePlaylist(UUID code);
	List<Playlist> findAll();
	List<Playlist> finByIdentifier(String identifier);
	List<Playlist> findByTitleContains(String title);
	void saveSongPlaylist(UUID codep, UUID codes);
	
}
