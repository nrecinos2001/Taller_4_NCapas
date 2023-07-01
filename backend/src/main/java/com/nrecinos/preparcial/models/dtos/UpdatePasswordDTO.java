package com.nrecinos.preparcial.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePasswordDTO {
	
	@NotEmpty(message= "El campo no puede estar vacio")
	private String identifier;
	
	@NotEmpty(message= "El campo no puede estar vacio")
	@Size(min =8, max = 8, message = "Exactamente 8 caracteres")
	private String password;
	
	@NotEmpty(message= "El campo no puede estar vacio")
	@Size(min =8, max = 8, message = "Exactamente 8 caracteres")
	private String newPassword;
}
