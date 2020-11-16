package br.com.caderneta.model;

import br.com.caderneta.model.AuthResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AuthResponse {

	private String jwt;
	
}

