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
import br.com.caderneta.model.Aluno;
import br.com.caderneta.model.dto.create.AlunoCreateDTO;
import br.com.caderneta.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

	private final AlunoService alunoService;

	@Autowired
	public AlunoResource(AlunoService alunoService) {
		this.alunoService = alunoService;
	}

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody AlunoCreateDTO aluno) {

		Aluno savedAluno = this.alunoService.create(aluno);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedAluno.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Aluno aluno)
			throws IdNotNullException, IdNotFoundException {

		this.alunoService.update(aluno);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Aluno> findById(@PathVariable Long id) throws IdNotNullException, IdNotFoundException {

		Aluno aluno = this.alunoService.findById(id);
		
		return ResponseEntity.ok(aluno);
	}
	
	@GetMapping
	public ResponseEntity<List<Aluno>> findAll(){
		return ResponseEntity.ok(this.alunoService.findAll());
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete(@PathVariable Long id) throws IdNotNullException, IdNotFoundException{
		this.alunoService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}
