package com.nrecinos.preparcial.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.nrecinos.preparcial.models.dtos.CreateSongDTO;
import com.nrecinos.preparcial.models.dtos.UpdateSongTitleDTO;
import com.nrecinos.preparcial.models.entities.Song;

public interface SongService {
	List<Song> findAll(String fragment);
	Song findSongById(UUID code);
	Page<Song> findAll(String fragment, Integer page, Integer size);
	
}
