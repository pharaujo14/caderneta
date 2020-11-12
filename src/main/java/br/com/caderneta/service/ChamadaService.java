package br.com.caderneta.service;


import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caderneta.exceptions.IdNotFoundException;
import br.com.caderneta.exceptions.IdNotNullException;

import br.com.caderneta.model.Chamada;

import br.com.caderneta.repository.ChamadaRepository;

@Service
public class ChamadaService {
	
	private final ChamadaRepository chamadaRepository;

	@Autowired
	public ChamadaService(ChamadaRepository chamadaRepository) {
		this.chamadaRepository = chamadaRepository;
	}

	public Chamada create(Chamada chamada) {

		chamada.setId(null);
		return this.chamadaRepository.save(chamada);
	}

		public Chamada update(Chamada nova) throws IdNotNullException, IdNotFoundException {

		Chamada antiga = this.findById(nova.getId());

		antiga.setFlagPresenca(nova.getFlagPresenca());
		

		return this.chamadaRepository.save(antiga);
	}

	public Chamada findById(Long id) throws IdNotNullException, IdNotFoundException {
		Optional.ofNullable(id).orElseThrow(() -> new IdNotNullException("chamada: " + id));

		return this.chamadaRepository.findById(id).orElseThrow(() -> new IdNotFoundException("chamada: " + id));

	}

	public List<Chamada> findAll() {
		return this.chamadaRepository.findAll();
	}

	public void deleteById(Long id) throws IdNotNullException, IdNotFoundException {
		this.findById(id);

		this.chamadaRepository.deleteById(id);
	}

}
