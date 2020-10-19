package br.com.caderneta.model;

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
	
	@Column(length = 50, nullable = false)
	private String data;	
	
	
	
	

}
