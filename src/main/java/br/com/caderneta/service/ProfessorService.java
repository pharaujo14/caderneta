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
import br.com.caderneta.model.dto.create.ProfessorCreateDTO;
import br.com.caderneta.repository.ProfessorRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfessorService {
	
	private final ProfessorRepository professorRepository;
	private final BCryptPasswordEncoder pEnconder;
	
	public Professor create(ProfessorCreateDTO professorDTO) {
		Professor professor = fromCreateDto(professorDTO);
		return this.professorRepository.save(professor);
	}
	
	public Professor update(Professor novo) throws IdNotFoundException, IdNotNullException {
		
		Professor antigo = this.findById(novo.getId());
		
		antigo.setNome(novo.getNome());
		antigo.setSobrenome(novo.getSobrenome());
		antigo.setEmail(novo.getEmail());
		antigo.setCpf(novo.getCpf());
		
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
	
	public Professor fromCreateDto(ProfessorCreateDTO professorDTO) {
		
		return Professor.builder()
				.nome(professorDTO.getNome())
				.sobrenome(professorDTO.getSobrenome())
				.email(professorDTO.getEmail())
				.cpf(professorDTO.getCpf())
				.usuario(
						Usuario.builder()
						.senha(pEnconder.encode(professorDTO.getSenha()))
						.username(professorDTO.getEmail())
						.role(RoleEnum.PROFESSOR)
						.build())
				.build();
				
	}

}
