package com.nrecinos.preparcial.services;

import java.util.List;
import java.util.UUID;

import com.nrecinos.preparcial.models.dtos.CreatePlaylistDTO;
import com.nrecinos.preparcial.models.entities.Playlist;

public interface PlaylistService {
	void savePlaylist(CreatePlaylistDTO playlist);
	void findPlaylistById(UUID code);
	void deletePlaylist(UUID code);
	List<Playlist> findAll();
}
