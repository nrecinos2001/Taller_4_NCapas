package com.nrecinos.preparcial.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nrecinos.preparcial.models.entities.Song;

public interface SongRepository extends JpaRepository<Song, UUID>{
	 @Query("SELECT s FROM Song s WHERE s.title LIKE %:titleFragment%")
	    List<Song> findAllByFragment(@Param("titleFragment") String titleFragment);
}
 