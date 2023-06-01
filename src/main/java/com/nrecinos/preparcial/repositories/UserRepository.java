package com.nrecinos.preparcial.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.nrecinos.preparcial.models.entities.Playlist;
import com.nrecinos.preparcial.models.entities.User;

public interface UserRepository extends ListCrudRepository<User, UUID>{
	 
	@Query("SELECT u FROM User u WHERE u.username = :identifier OR u.email = :identifier")
	 User findByUsernameOrEmail(@Param("identifier") String identifier);
}


