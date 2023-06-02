package com.nrecinos.preparcial.repositories;


import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.nrecinos.preparcial.models.entities.SongXPlaylist;

public interface SongXPlaylistRepository extends ListCrudRepository<SongXPlaylist, UUID>{
	
}
 