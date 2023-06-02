package com.nrecinos.preparcial.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.User;

public interface PlaylistRepository extends ListCrudRepository<Playlist, UUID>{
	Playlist findByCode(UUID code);
	boolean existsByUserAndTitle(User user, String title);
	List<Playlist> findByUser(User user);
	List<Playlist> findByUserAndTitleContains(User user, String title);
	@Query("SELECT p FROM Playlist p JOIN FETCH p.songXPlaylist sp JOIN FETCH sp.song WHERE p = :playlist")
    Playlist findPlaylistWithSongs(@Param("playlist") Playlist playlist);
}
