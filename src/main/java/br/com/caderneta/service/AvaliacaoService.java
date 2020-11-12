package br.com.caderneta.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.caderneta.exceptions.IdNotFoundException;
import br.com.caderneta.exceptions.IdNotNullException;
import br.com.caderneta.model.Avaliacao;


import br.com.caderneta.repository.AvaliacaoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AvaliacaoService {
	
	private final AvaliacaoRepository avaliacaoRepository;

	
	public Avaliacao create(Avaliacao avaliacao) {
		
		avaliacao.setId(null);
		return this.avaliacaoRepository.save(avaliacao);
	}
	
	public Avaliacao update(Avaliacao novo) throws IdNotFoundException, IdNotNullException {
		
		Avaliacao antigo = this.findById(novo.getId());
		
		antigo.setNome(novo.getNome());
		antigo.setData(novo.getData());
		antigo.setNota(novo.getNota());
		
		return this.avaliacaoRepository.save(antigo);		
	}
	
	public Avaliacao findById(Long id) throws IdNotFoundException, IdNotNullException {
		Optional
				.ofNullable(id)
				.orElseThrow( () -> new IdNotNullException("avaliação: " + id));
		
		return this.avaliacaoRepository.findById(id)
					.orElseThrow( () -> new IdNotFoundException("avaliação: " + id));
	}
		
	public List<Avaliacao> findAll(){
		return this.avaliacaoRepository.findAll();
	}
	
	public void deleteById(Long id) throws IdNotFoundException, IdNotNullException {
		this.findById(id);
		
		this.avaliacaoRepository.deleteById(id);
	}

}
