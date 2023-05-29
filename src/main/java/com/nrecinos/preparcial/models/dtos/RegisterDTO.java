package com.nrecinos.preparcial.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterDTO {
	
	@NotEmpty(message= "El campo no puede estar vacio")
	private String username;
	
	@NotEmpty(message= "El campo no puede estar vacio")
	private String email;
	
	@NotEmpty
	@Size(min =8, max = 8, message = "Exactamente 8 caracteres")
	private String password;
}
