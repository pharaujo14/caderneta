package br.com.caderneta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caderneta.exceptions.IdNotFoundException;
import br.com.caderneta.exceptions.IdNotNullException;

import br.com.caderneta.model.AlunoAula;
import br.com.caderneta.repository.AlunoAulaRepository;


@Service
public class AlunoAulaService {

	private final AlunoAulaRepository alunoAulaRepository;

	@Autowired
	public AlunoAulaService(AlunoAulaRepository alunoAulaRepository) {
		this.alunoAulaRepository = alunoAulaRepository;
	}

	public AlunoAula create(AlunoAula alunoAula) {
		alunoAula.setId(null);
		return this.alunoAulaRepository.save(alunoAula);
	}

	public AlunoAula update(AlunoAula novo) throws IdNotNullException, IdNotFoundException {
		AlunoAula antigo = this.findById(novo.getId());
		
		antigo.setPresenca(novo.getPresenca());
		antigo.setNota(novo.getNota());
		
		return this.alunoAulaRepository.save(antigo);

	}
	
	public AlunoAula findById(Long id) throws IdNotNullException, IdNotFoundException {
		Optional
				.ofNullable(id)
				.orElseThrow( () -> new IdNotNullException("Aluno Aula: " + id));
		
		return this.alunoAulaRepository.findById(id)
					.orElseThrow( () -> new IdNotFoundException("Aluno Aula: " + id));
	}
	
	public List<AlunoAula> findAll(){
		return this.alunoAulaRepository.findAll();
	}
	
	public void deleteById(Long id) throws IdNotNullException, IdNotFoundException {
		this.findById(id);
		
		this.alunoAulaRepository.deleteById(id);
		
	}
	
}
