package br.com.caderneta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caderneta.exceptions.IdNotFoundException;
import br.com.caderneta.exceptions.IdNotNullException;
import br.com.caderneta.model.Aluno;
import br.com.caderneta.repository.AlunoRepository;

@Service
public class AlunoService {

	private final AlunoRepository alunoRepository;

	@Autowired
	public AlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	public Aluno create(Aluno aluno) {
		aluno.setId(null);
		return this.alunoRepository.save(aluno);
	}

	public Aluno update(Aluno novo) throws IdNotNullException, IdNotFoundException {
		Aluno antigo = this.findById(novo.getId());
		
		antigo.setNome(novo.getNome());
		antigo.setSobrenome(novo.getSobrenome());
		antigo.setEmail(novo.getEmail());
		antigo.setCpf(novo.getCpf());
		antigo.setFoto(novo.getFoto());
		antigo.setSenha(novo.getSenha());
		
		return this.alunoRepository.save(antigo);

	}
	
	public Aluno findById(Long id) throws IdNotNullException, IdNotFoundException {
		Optional
				.ofNullable(id)
				.orElseThrow( () -> new IdNotNullException("Aluno: " + id));
		
		return this.alunoRepository.findById(id)
					.orElseThrow( () -> new IdNotFoundException("Aluno: " + id));
	}
	
	public List<Aluno> findAll(){
		return this.alunoRepository.findAll();
	}
	
	public void deleteById(Long id) throws IdNotNullException, IdNotFoundException {
		this.findById(id);
		
		this.alunoRepository.deleteById(id);
		
	}

}
