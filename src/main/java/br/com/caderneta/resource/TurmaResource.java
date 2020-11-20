package br.com.caderneta.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import br.com.caderneta.model.Turma;
import br.com.caderneta.service.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaResource {

	private final TurmaService turmaService;
	
	@Autowired
	public TurmaResource(TurmaService turmaService) {
		this.turmaService = turmaService;
	}
	
    @PatchMapping("/{id}/addAlunos")
    public ResponseEntity<Void> addAluno(@RequestParam Long id, @RequestParam String email) throws IdNotFoundException, IdNotNullException{
    	this.turmaService.addAluno(id, email);
    	return ResponseEntity.noContent().build();
    	
    }

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Turma turma) {

		turma = this.turmaService.create(turma);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(turma.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Turma turma)
			throws IdNotFoundException, IdNotNullException {
		turma.setId(id);

		this.turmaService.update(turma);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Turma> findById(@PathVariable Long id) throws IdNotFoundException, IdNotNullException {

		Turma turma = this.turmaService.findById(id);

		return ResponseEntity.ok(turma);

	}
	
	@GetMapping
	public ResponseEntity<List<Turma>> findAll(){
		return ResponseEntity.ok(this.turmaService.findAll());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws IdNotFoundException, IdNotNullException{
		this.turmaService.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
	}

}



