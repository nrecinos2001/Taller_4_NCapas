package com.nrecinos.preparcial.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.nrecinos.preparcial.models.entities.Playlist;

public interface PlaylistRepository extends ListCrudRepository<Playlist, UUID>{
	
	
	List<Playlist> findByTitleContains(String title);

	
}
