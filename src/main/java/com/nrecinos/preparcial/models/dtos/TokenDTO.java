package com.nrecinos.preparcial.models.dtos;

import com.nrecinos.preparcial.models.entities.Token;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDTO {
	private String token;
	
	public TokenDTO(Token token) {
		this.token = token.getContent();
	}
	
}
