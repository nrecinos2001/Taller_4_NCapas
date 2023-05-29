package com.nrecinos.preparcial.services;

import java.util.UUID;

import com.nrecinos.preparcial.models.dtos.CreateSongDTO;

public interface SongService {
	void saveSong(CreateSongDTO song) throws Exception;
	void findOneById(UUID code);
	void getAll();
	void deleteSongById(UUID code)  throws Exception;
}
