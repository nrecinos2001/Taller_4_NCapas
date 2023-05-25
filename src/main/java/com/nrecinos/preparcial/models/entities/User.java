package com.nrecinos.preparcial.models.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@ToString
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "code")
	private UUID code;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;
}
