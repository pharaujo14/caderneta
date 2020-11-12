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

import br.com.caderneta.model.Chamada;

import br.com.caderneta.service.ChamadaService;


@RestController
@RequestMapping("/chamada")
public class ChamadaResource {
	
private final ChamadaService chamadaService;
	
	@Autowired
	public ChamadaResource(ChamadaService chamadaService) {
		this.chamadaService = chamadaService;
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Chamada chamada){
		
		chamada = this.chamadaService.create(chamada);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamada.getId())
					.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Chamada chamada) throws IdNotNullException, IdNotFoundException{
		
		chamada.setId(id);
		
		this.chamadaService.update(chamada);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Chamada> findById(@PathVariable Long id) throws IdNotNullException, IdNotFoundException{
		
		Chamada chamada = this.chamadaService.findById(id);
		
		return ResponseEntity.ok(chamada);
	}
	
	@GetMapping
	public ResponseEntity<List<Chamada>> findAll(){
		return ResponseEntity.ok(this.chamadaService.findAll());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) throws IdNotNullException, IdNotFoundException{
		this.chamadaService.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}
