package br.com.caderneta.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "professores")
public class Professor extends Pessoa{	
	
	@OneToMany(mappedBy="professor")
	private Set<Turma> turmas;

}
