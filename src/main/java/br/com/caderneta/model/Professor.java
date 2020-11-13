package br.com.caderneta.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "professores")
@Getter
@Setter
@NoArgsConstructor
public class Professor extends Pessoa{	
	
	@OneToMany(mappedBy="professor")
	@JsonIgnore
	private Set<Turma> turmas;

	@Builder
	public Professor(Long id, String nome, String sobrenome, String email, String cpf, Usuario usuario, Set<Turma> turmas) {
		super(id, nome, sobrenome, email, cpf, usuario);
		this.turmas = turmas;
	}
	
	

}
