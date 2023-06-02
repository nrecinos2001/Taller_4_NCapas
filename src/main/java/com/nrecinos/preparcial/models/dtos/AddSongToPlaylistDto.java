package com.nrecinos.preparcial.models.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddSongToPlaylistDto {
	@NotEmpty(message = "song_code no debe estar vacio")
	private UUID song_code;
	@NotEmpty(message = "playlist_code no debe estar vacio")
	private UUID playlist_code;
}
