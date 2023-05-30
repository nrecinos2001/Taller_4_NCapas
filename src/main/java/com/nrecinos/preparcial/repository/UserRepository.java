package com.nrecinos.preparcial.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.User;

public interface UserRepository extends ListCrudRepository<User, UUID>{


	
	List<Playlist> getPlaylist(String identifier);
	List<Playlist> getPlaylistTitle(String identifier, String fragment);

	@Query("SELECT * FROM user WHERE user.email = :username")
	User findOneByIdentificator(@Param("username")String username);
}


