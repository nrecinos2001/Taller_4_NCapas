package com.nrecinos.preparcial.services;

import java.rmi.ServerException;
import java.util.List;
import java.util.UUID;

import com.nrecinos.preparcial.models.dtos.CreatePlaylistDTO;
import com.nrecinos.preparcial.models.dtos.PlaylistWithSongsDTO;
import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.User;

public interface PlaylistService {
	void savePlaylist(CreatePlaylistDTO playlist, User user);
	Playlist findPlaylistById(UUID code);
	PlaylistWithSongsDTO findPlaylistWithSongById(UUID code);
	void deletePlaylist(UUID code);
	List<Playlist> findAll();
	List<Playlist> finByIdentifier(String identifier);
	List<Playlist> findByTitleContains(String title);
	List<Playlist> findByUser(User user);
	void saveSongPlaylist(UUID codep, UUID codes) throws ServerException;;
  boolean existsByUserAndTitle(User user, String title);

}
