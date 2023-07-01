package com.nrecinos.preparcial.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterDTO {
	
	@NotEmpty(message= "El campo no puede estar vacio")
	private String username;
	
	@NotEmpty(message= "El campo no puede estar vacio")
	private String email;
	
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!¡¿?])[A-Za-z\\d@#$%^&+=!¡¿?]{8,}$",
            message = "Password must have at least 8 characters and the required and the required characters to fulfill")
	private String password;
}
