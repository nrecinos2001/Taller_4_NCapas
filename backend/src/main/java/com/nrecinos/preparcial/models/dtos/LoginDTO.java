package com.nrecinos.preparcial.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {
	
	@NotEmpty(message = "El campo no debe estar vacío")
	private String identificator;
	
	@NotEmpty(message = "El campo no debe estar vacío")
	private String password;
}
