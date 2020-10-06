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
import br.com.caderneta.model.Professor;
import br.com.caderneta.service.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorResource {

	private final ProfessorService professorService;

	@Autowired
	public ProfessorResource(ProfessorService professorService) {
		this.professorService = professorService;
	}

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Professor professor) {

		professor = this.professorService.create(professor);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(professor.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Professor professor)
			throws IdNotFoundException, IdNotNullException {
		professor.setId(id);

		this.professorService.update(professor);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Professor> findById(@PathVariable Long id) throws IdNotFoundException, IdNotNullException {

		Professor professor = this.professorService.findById(id);

		return ResponseEntity.ok(professor);

	}
	
	@GetMapping
	public ResponseEntity<List<Professor>> findAll(){
		return ResponseEntity.ok(this.professorService.findAll());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws IdNotFoundException, IdNotNullException{
		this.professorService.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
	}

}
