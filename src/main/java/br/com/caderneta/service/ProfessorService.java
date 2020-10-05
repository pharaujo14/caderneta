package br.com.caderneta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caderneta.exceptions.IdNotFoundException;
import br.com.caderneta.exceptions.IdNotNullException;
import br.com.caderneta.model.Professor;
import br.com.caderneta.repository.ProfessorRepository;

@Service
public class ProfessorService {
	
	private final ProfessorRepository professorRepository;
	
	@Autowired
	public ProfessorService(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}
	
	public Professor create(Professor professor) {
		professor.setId(null);
		return this.professorRepository.save(professor);
	}
	
	public Professor update(Professor novo) throws IdNotFoundException, IdNotNullException {
		
		Professor antigo = this.findById(novo.getId());
		
		antigo.setNome(novo.getNome());
		antigo.setSobrenome(novo.getSobrenome());
		antigo.setEmail(novo.getEmail());
		antigo.setCpf(novo.getCpf());
		antigo.setFoto(novo.getFoto());
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

}
