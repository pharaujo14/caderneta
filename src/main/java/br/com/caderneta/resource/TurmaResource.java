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
import br.com.caderneta.model.Turma;
import br.com.caderneta.model.dto.create.TurmaCreateDTO;
import br.com.caderneta.model.dto.delete.TurmaDeleteDTO;
import br.com.caderneta.model.dto.update.TurmaUpdateDTO;
import br.com.caderneta.service.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaResource {

	private final TurmaService turmaService;
	
	@Autowired
	public TurmaResource(TurmaService turmaService) {
		this.turmaService = turmaService;
	}
	
    @PutMapping("/addAlunos")
    public ResponseEntity<Void> addAluno(@RequestBody TurmaUpdateDTO turmaUpdate){
    	this.turmaService.addAluno(turmaUpdate);
    	return ResponseEntity.noContent().build();
    	
    }
    
    @PutMapping("/deleteAlunos")
    public ResponseEntity<Void> deleteAluno(@RequestBody TurmaUpdateDTO turmaUpdate){
    	this.turmaService.deleteAluno(turmaUpdate);
    	return ResponseEntity.noContent().build();
    	
    }

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody TurmaCreateDTO turma) {

		Turma savedTurma = this.turmaService.create(turma);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTurma.getId())
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
	
	@GetMapping("/alunos")
	public ResponseEntity<List<Turma>> findByAluno(){

		List<Turma> turmas = turmaService.findByAluno();

		return ResponseEntity.ok(turmas);

	}
	
	@GetMapping
	public ResponseEntity<List<Turma>> findAll(){
		return ResponseEntity.ok(this.turmaService.findAll());
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestBody TurmaDeleteDTO deleteDTO ) throws IdNotFoundException, IdNotNullException{
		this.turmaService.deleteById(deleteDTO);
		
		return ResponseEntity.noContent().build();
		
	}

}



