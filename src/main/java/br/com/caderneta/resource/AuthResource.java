package br.com.caderneta.resource;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caderneta.model.AuthResponse;
import br.com.caderneta.model.Credential;
import br.com.caderneta.service.AuthService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthResource {
	
	private final AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login( @RequestBody @Valid Credential credential){
		
		AuthResponse login = this.authService.authenticate(credential);
		
		return ResponseEntity.ok(login);
		
	
	}

}