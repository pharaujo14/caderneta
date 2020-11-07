package br.com.caderneta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.caderneta.exceptions.IdNotFoundException;
import br.com.caderneta.exceptions.IdNotNullException;
import br.com.caderneta.model.Professor;
import br.com.caderneta.model.RoleEnum;
import br.com.caderneta.model.Usuario;
import br.com.caderneta.repository.ProfessorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfessorService {
	
	private final ProfessorRepository professorRepository;
	private final BCryptPasswordEncoder pEnconder;
	
	public Professor create(Professor professor) {
		professor = fromCreate(professor);
		return this.professorRepository.save(professor);
	}
	
	public Professor update(Professor novo) throws IdNotFoundException, IdNotNullException {
		
		Professor antigo = this.findById(novo.getId());
		
		antigo.setNome(novo.getNome());
		antigo.setSobrenome(novo.getSobrenome());
		antigo.setEmail(novo.getEmail());
		antigo.setCpf(novo.getCpf());
		antigo.setSenha(novo.getSenha());
		
		return this.professorRepository.save(antigo);
	}
	
	public Professor findById(Long id) throws IdNotFoundException, IdNotNullException {
		Optional
				.ofNullable(id)
				.orElseThrow( () -> new IdNotNullException("professor: " + id));
		
		return this.professorRepository.findById(id)
					.orElseThrow( () -> new IdNotFoundException("professor: " + id));
	}
	
	public List<Professor> findAll(){
		return this.professorRepository.findAll();
	}
	
	public void deleteById(Long id) throws IdNotFoundException, IdNotNullException {
		this.findById(id);
		
		this.professorRepository.deleteById(id);
	}
	
	public Professor fromCreate(Professor professor) {
		
		return Professor.builder()
				.nome(professor.getNome())
				.sobrenome(professor.getSobrenome())
				.email(professor.getEmail())
				.cpf(professor.getCpf())
				.senha(pEnconder.encode(professor.getSenha())) // ver isso com o Gustavo
				.usuario(
						Usuario.builder()
						.senha(pEnconder.encode(professor.getSenha()))
						.username(professor.getEmail())
						.role(RoleEnum.PROFESSOR)
						.build())
				.build();
				
	}

}
