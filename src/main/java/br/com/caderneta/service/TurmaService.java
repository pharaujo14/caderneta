package br.com.caderneta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caderneta.exceptions.IdNotFoundException;
import br.com.caderneta.exceptions.IdNotNullException;
import br.com.caderneta.model.Turma;
import br.com.caderneta.repository.TurmaRepository;

@Service
public class TurmaService {
	
	private final TurmaRepository turmaRepository;
	
	@Autowired
	public TurmaService(TurmaRepository turmaRepository) {
		this.turmaRepository = turmaRepository;
	}
	
	public Turma create(Turma turma) {
		turma.setId(null);
		return this.turmaRepository.save(turma);
	}
	
	public Turma update(Turma novo) throws IdNotFoundException, IdNotNullException {
		
		Turma antigo = this.findById(novo.getId());
		
		antigo.setNome(novo.getNome());
		antigo.setLocal(novo.getLocal());
		antigo.setHorario(novo.getHorario());
		antigo.setDia(novo.getDia());
		antigo.setInicio(novo.getInicio());
		antigo.setFim(novo.getFim());
			
		return this.turmaRepository.save(antigo);
	}
	
	public Turma findById(Long id) throws IdNotFoundException, IdNotNullException {
		Optional
				.ofNullable(id)
				.orElseThrow( () -> new IdNotNullException("turma: " + id));
		
		return this.turmaRepository.findById(id)
					.orElseThrow( () -> new IdNotFoundException("turma: " + id));
	}
	
	public List<Turma> findAll(){
		return this.turmaRepository.findAll();
	}
	
	public void deleteById(Long id) throws IdNotFoundException, IdNotNullException {
		this.findById(id);
		
		this.turmaRepository.deleteById(id);
	}

}