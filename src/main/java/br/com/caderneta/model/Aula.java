package br.com.caderneta.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
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
@Table(name = "aulas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Aula {
	
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(length = 50, nullable = false)
	private String nome;	
	
	@Column(nullable = false)	
	private LocalDate data;	
		
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "turma_id")
	private Turma turma;	
	
	@Column(name = "horario_inicio", length = 150, nullable = false)
	private String horarioInicio;
	
	@Column(name = "horario_fim", length = 150, nullable = false)
	private String horarioFim;
	
	@Column(name = "conteudo", nullable = false)
	private String conteudo;

}
