package br.com.caderneta.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Chamada {
	
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "aula_id")
	private Aula aula;
	
	@ManyToOne
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;	
	
	@Column(length = 1, nullable = false)
	private String flagPresenca;
}
