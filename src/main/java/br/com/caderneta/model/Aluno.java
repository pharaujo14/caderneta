package br.com.caderneta.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.ManyToMany;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alunos")
@Getter
@Setter
@NoArgsConstructor
public class Aluno extends Pessoa {

	@ManyToMany(mappedBy = "alunos")
	@JsonIgnore
	private Set<Turma> turmas;

	@Column
	private Float mediaPresenca;

	@Column
	private Float mediaNotas;

	@Builder
	public Aluno(Long id, String nome, String sobrenome, String email, String cpf, Usuario usuario,
			Set<Turma> turmas, Float mediaPresenca, Float mediaNotas) {
		super(id, nome, sobrenome, email, cpf, usuario);
		this.turmas = turmas;
		this.mediaPresenca = mediaPresenca;
		this.mediaNotas = mediaNotas;
	}

}
