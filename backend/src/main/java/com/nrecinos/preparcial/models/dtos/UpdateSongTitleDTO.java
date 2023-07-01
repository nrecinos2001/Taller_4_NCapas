package com.nrecinos.preparcial.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateSongTitleDTO {

	@NotEmpty(message= "El identificador no puede estar vacio")
	private String code;
	
	@NotEmpty(message= "El titulo no puede estar vacio")
	private String newTitle;	
	
}
