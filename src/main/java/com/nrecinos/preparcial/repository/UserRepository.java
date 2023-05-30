package com.nrecinos.preparcial.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.nrecinos.preparcial.models.entities.User;

public interface UserRepository extends ListCrudRepository<User, UUID>{
	@Query("SELECT * FROM user WHERE user.email = :username")
	User findOneByIdentificator(@Param("username")String username);
}

