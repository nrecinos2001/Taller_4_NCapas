package com.nrecinos.preparcial.models.dtos;

import com.nrecinos.preparcial.models.entities.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatePlaylistDTO {

	@NotEmpty(message = "El campo no debe estar vacío")
	@Size(min =100, max = 100, message = "Exactamente 100 caracteres")
	private String title;
	
	private String description;
	
	private String identifier;
		
}
