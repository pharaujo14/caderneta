package br.com.caderneta.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.caderneta.model.AuthResponse;
import br.com.caderneta.model.Credential;
import br.com.caderneta.model.Usuario;
import br.com.caderneta.security.JwtUtil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final UsuarioService usuarioService;
	
    public AuthResponse authenticate(Credential credentials) {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            credentials.getUsername(),
            credentials.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final Usuario user = this.usuarioService.findByUsername(credentials.getUsername());
        final String token = jwtUtil.generateToken(user);
        return AuthResponse.builder().jwt(token).build();
    }

}