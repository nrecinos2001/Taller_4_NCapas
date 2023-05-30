package com.nrecinos.preparcial.services;

import java.util.List;
import java.util.UUID;

import com.nrecinos.preparcial.models.dtos.CreateSongDTO;
import com.nrecinos.preparcial.models.dtos.UpdateSongTitleDTO;
import com.nrecinos.preparcial.models.entities.Song;

public interface SongService {
	void saveSong(CreateSongDTO song) throws Exception;
	Song findOneById(UUID code);
	void deleteSongById(UUID code)  throws Exception;
	void updateTitle(UpdateSongTitleDTO title, Song song);
	List<Song> findAll(String fragment);
}
