package br.com.caderneta.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "turmas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Turma {
	
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(length = 50, nullable = false)
	private String nome;	
	
	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;
	
	@Column(length = 150, nullable = false)
	private String local;
	
	@Column(name = "horario_inicio", length = 150, nullable = false)
	private String horarioInicio;
	
	@Column(name = "horario_fim", length = 150, nullable = false)
	private String horarioFim;
	
	@Column(length = 150, nullable = false)
	private String dia;
	
	@Column(length = 150, nullable = false)
	private String inicio;
	
	@Column(length = 150, nullable = false)
	private String fim;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="alunos_turmas",
		joinColumns = @JoinColumn(name = "turma_id"),
		inverseJoinColumns = @JoinColumn(name = "aluno_id"))
	private Set<Aluno> alunos;

}
