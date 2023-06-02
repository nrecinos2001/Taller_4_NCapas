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
	private String title;
	
	@NotEmpty(message = "El campo no debe estar vacío")
	private String description;
	
	@NotEmpty(message = "El campo no debe estar vacío")
	private String identifier;
		
}
