package br.com.caderneta.model;
	

	import java.time.LocalDate;


import javax.persistence.Column;
	import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
	import javax.persistence.Id;
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
	public class Avaliacao {
		
		@Id
		@GeneratedValue
		@EqualsAndHashCode.Include
		private Long id;
		
		@Column(length = 50, nullable = false)
		private String nome;
		
		@Column
		private LocalDate data;
		
		@Column
		private Float nota;
				
		@Column(name = "professor_id")
		private Long professorId;

		@Column(name = "aluno_id")
		private Long alunoId;
		
		
	}


