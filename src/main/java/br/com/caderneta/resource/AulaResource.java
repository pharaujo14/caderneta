package br.com.caderneta.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import br.com.caderneta.model.Aula;
import br.com.caderneta.request.AulasRequest;
import br.com.caderneta.service.AulaService;

@RestController
@RequestMapping("/aulas")
public class AulaResource {
	
	private final AulaService aulaService;
	
	@Autowired
	public AulaResource(AulaService aulaService) {
		this.aulaService = aulaService;
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Aula aula){
		
		aula = this.aulaService.create(aula);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aula.getId())
					.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping("/emLote")
	public ResponseEntity<Void> createAulas(@RequestBody AulasRequest request) {				

		this.aulaService.createAulas(request.getDias(), request.getTurma());
				
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Aula aula) throws IdNotNullException, IdNotFoundException{
		
		aula.setId(id);
		
		this.aulaService.update(aula);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aula> findById(@PathVariable Long id) throws IdNotNullException, IdNotFoundException{
		
		Aula aula = this.aulaService.findById(id);
		
		return ResponseEntity.ok(aula);
	}
	
	@GetMapping
	public ResponseEntity<List<Aula>> findAll(){
		return ResponseEntity.ok(this.aulaService.findAll());
	}
	
	@GetMapping("/professores")
	public ResponseEntity<List<Aula>> findByProfessor(){
		List<Aula> aulas = aulaService.findByProfessor();

		return ResponseEntity.ok(aulas);
	}
	
	@GetMapping("/professores/dashboard")
	public ResponseEntity<List<Aula>> findByProfessorDashboard(){
		List<Aula> aulas = aulaService.findByProfessorDashboard();

		return ResponseEntity.ok(aulas);
	}
	
	@GetMapping("/alunos")
	public ResponseEntity<List<Aula>> findByAluno(){
		List<Aula> aulas = aulaService.findByAluno();

		return ResponseEntity.ok(aulas);
	}
	
	@GetMapping("/alunos/dashboard")
	public ResponseEntity<List<Aula>> findByAlunoDashboard(){
		List<Aula> aulas = aulaService.findByAlunoDashboard();

		return ResponseEntity.ok(aulas);
	}
	
	@GetMapping("/turma/{id}")
	public ResponseEntity<List<Aula>> findByTurma(@PathVariable Long id){

		List<Aula> aulas = aulaService.findByTurma_id(id);

		return ResponseEntity.ok(aulas);

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws IdNotNullException, IdNotFoundException{
		this.aulaService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}
