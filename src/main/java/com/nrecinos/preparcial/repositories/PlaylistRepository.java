package com.nrecinos.preparcial.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.User;

public interface PlaylistRepository extends ListCrudRepository<Playlist, UUID>{
	boolean existsByUserAndTitle(User user, String title);
	List<Playlist> findByUser(User user);
}
