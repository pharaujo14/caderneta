package br.com.caderneta.model;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alunoaulas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoAula {

	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;
	
	
	private Float nota;
	
	
	private Boolean presenca;
	
	@ManyToMany
	private Aluno aluno;
	
	@ManyToMany
	private Aula aula;	
	
}
