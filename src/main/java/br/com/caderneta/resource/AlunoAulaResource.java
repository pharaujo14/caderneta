package br.com.caderneta.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

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

import br.com.caderneta.model.AlunoAula;
import br.com.caderneta.service.AlunoAulaService;


@RestController
@RequestMapping("/aluno-aulas")
public class AlunoAulaResource {
	
	private final AlunoAulaService alunoAulaService;
	
	@Autowired
	public AlunoAulaResource(AlunoAulaService alunoAulaService) {
		this.alunoAulaService = alunoAulaService;
	}

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody AlunoAula alunoAula) {

		alunoAula = this.alunoAulaService.create(alunoAula);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(alunoAula.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody AlunoAula alunoAula)
			throws IdNotNullException, IdNotFoundException {

		this.alunoAulaService.update(alunoAula);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<AlunoAula> findById(@PathVariable Long id) throws IdNotNullException, IdNotFoundException {

		AlunoAula alunoAula = this.alunoAulaService.findById(id);
		
		return ResponseEntity.ok(alunoAula);
	}
	
	@GetMapping
	public ResponseEntity<List<AlunoAula>> findAll(){
		return ResponseEntity.ok(this.alunoAulaService.findAll());
	}	

}
