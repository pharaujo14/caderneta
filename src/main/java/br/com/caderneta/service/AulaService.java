package br.com.caderneta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caderneta.exceptions.IdNotFoundException;
import br.com.caderneta.exceptions.IdNotNullException;
import br.com.caderneta.model.Aula;
import br.com.caderneta.repository.AulaRepository;

@Service
public class AulaService {
	
	private final AulaRepository aulaRepository;
	
	@Autowired
	public AulaService(AulaRepository aulaRepository) {
		this.aulaRepository = aulaRepository;
	}
	
	public Aula create(Aula aula) {
		aula.setId(null);
		return this.aulaRepository.save(aula);
	}
	
	public Aula update(Aula nova) throws IdNotNullException, IdNotFoundException {
		
		Aula antiga = this.findById(nova.getId());
		
		antiga.setNome(nova.getNome());
		antiga.setData(nova.getData());
		
		return this.aulaRepository.save(antiga);
	}
	
	public Aula findById(Long id) throws IdNotNullException, IdNotFoundException {
		Optional
				.ofNullable(id)
				.orElseThrow( () -> new IdNotNullException("aula: " + id));
		
		return this.aulaRepository.findById(id)
					.orElseThrow( () -> new IdNotFoundException("aula: " + id));
		
	}
	
	public List<Aula> findAll(){
		return this.aulaRepository.findAll();
	}
	
	public void deleteById(Long id) throws IdNotNullException, IdNotFoundException {
		this.findById(id);
		
		this.aulaRepository.deleteById(id);
	}

}
