package br.com.caderneta.resource;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.caderneta.exceptions.IdNotFoundException;
import br.com.caderneta.exceptions.IdNotNullException;
import br.com.caderneta.model.Professor;
import br.com.caderneta.model.Turma;
import br.com.caderneta.model.dto.create.ProfessorCreateDTO;
import br.com.caderneta.service.ProfessorService;
import br.com.caderneta.service.TurmaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/professores")
@AllArgsConstructor
public class ProfessorResource {

	private final ProfessorService professorService;
	private final TurmaService turmaService;
	
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody ProfessorCreateDTO professor) {

		Professor savedProfessor = this.professorService.create(professor);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedProfessor.getId())
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
	
	@GetMapping("/user")
	public ResponseEntity<Professor> findByUsuario(@RequestParam Long id) throws IdNotNullException, IdNotFoundException {

		Professor professor = this.professorService.findByUsuario(id);
		
		return ResponseEntity.ok(professor);
	}
	
	@GetMapping
	public ResponseEntity<List<Professor>> findAll(){
		return ResponseEntity.ok(this.professorService.findAll());
	}
	
	@GetMapping("/{id}/turmas")
	public ResponseEntity<List<Turma>> findTurmaByProfessor(){
		List<Turma> turmas = this.turmaService.findByProfessor();
		
		return ResponseEntity.ok(turmas);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws IdNotFoundException, IdNotNullException{
		this.professorService.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
	}

}
