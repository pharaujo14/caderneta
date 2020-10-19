package br.com.caderneta.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "alunos")
public class Aluno extends Pessoa{
	
	@ManyToMany
	@JoinTable(
		name="alunos_turmas",
		joinColumns = @JoinColumn(name = "aluno_id"),
		inverseJoinColumns = @JoinColumn(name = "turma_id"))
	private Set<Turma> turmas;


}
