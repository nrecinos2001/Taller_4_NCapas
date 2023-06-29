package com.nrecinos.preparcial.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.nrecinos.preparcial.models.entities.Song;

public interface SongRepository extends JpaRepository<Song, UUID>{
	 Page<Song> findByTitleContaining(String titleFragment, Pageable pageable);
	 Song findByCode(UUID code); 
}
 