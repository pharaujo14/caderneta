package br.com.caderneta.model;

import java.time.LocalDate;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	//@Column
	//private List<Dia> dias;	
	
	@Column(length = 150, nullable = false, columnDefinition = "DATE")
	private LocalDate inicio;
	
	@Column(length = 150, nullable = false)
	private LocalDate fim;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="alunos_turmas",
		joinColumns = @JoinColumn(name = "turma_id"),
		inverseJoinColumns = @JoinColumn(name = "aluno_id"))
	private Set<Aluno> alunos;

	@OneToMany(mappedBy="turma")
	@JsonIgnore
	private Set<Aula> aulas;
}
