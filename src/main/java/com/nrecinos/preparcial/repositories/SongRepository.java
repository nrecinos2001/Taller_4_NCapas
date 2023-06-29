package com.nrecinos.preparcial.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.nrecinos.preparcial.models.entities.Song;

public interface SongRepository extends JpaRepository<Song, UUID>{
	 List<Song> findByTitleContaining(String titleFragment);
	 Song findByCode(UUID code); 
}
 