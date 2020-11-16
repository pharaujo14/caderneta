package br.com.caderneta.service;



import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.caderneta.model.Usuario;
import br.com.caderneta.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Service
@AllArgsConstructor
@Builder
public class UsuarioService {
	
    private final UsuarioRepository usuarioRepository;

    public Usuario findByUsername(String username) {
        return this.usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário " + username + "não encontrado"));
    }
	
	
	
	
	
	
	
	
	
	
	
}
