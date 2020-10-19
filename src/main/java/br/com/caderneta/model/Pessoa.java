package br.com.caderneta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Pessoa{

	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	@Column(length = 150, nullable = false)
	private String sobrenome;
	
	@Column(unique = true, length = 100, nullable = false)
	private String email;
	
	@Column(length = 15, nullable = false)
	private String cpf;
	
	@Column(nullable = false)
	private String senha;

}
