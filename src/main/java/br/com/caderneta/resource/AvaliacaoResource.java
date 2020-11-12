package br.com.caderneta.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.caderneta.exceptions.IdNotFoundException;
import br.com.caderneta.exceptions.IdNotNullException;
import br.com.caderneta.model.Avaliacao;

import br.com.caderneta.service.AvaliacaoService;


@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoResource {
	
	private final AvaliacaoService avaliacaoService;

	@Autowired
	public AvaliacaoResource(AvaliacaoService avaliacaoService) {
		this.avaliacaoService = avaliacaoService;
	}
	
    @PostMapping
	public ResponseEntity<Void> create(@RequestBody Avaliacao avaliacao) {

		avaliacao = this.avaliacaoService.create(avaliacao);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(avaliacao.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Avaliacao avaliacao)
			throws IdNotFoundException, IdNotNullException {
		avaliacao.setId(id);

		this.avaliacaoService.update(avaliacao);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Avaliacao> findById(@PathVariable Long id) throws IdNotFoundException, IdNotNullException {

		Avaliacao avaliacao = this.avaliacaoService.findById(id);

		return ResponseEntity.ok(avaliacao);

	}
	
	@GetMapping
	public ResponseEntity<List<Avaliacao>> findAll(){
		return ResponseEntity.ok(this.avaliacaoService.findAll());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws IdNotFoundException, IdNotNullException{
		this.avaliacaoService.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
	}

}
