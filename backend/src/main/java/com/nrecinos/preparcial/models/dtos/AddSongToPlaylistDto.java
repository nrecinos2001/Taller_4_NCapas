package com.nrecinos.preparcial.models.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddSongToPlaylistDto {
	@NotEmpty(message = "songCode no debe estar vacio")
	private String songCode;
}
