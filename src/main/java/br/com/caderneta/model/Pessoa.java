package br.com.caderneta.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column()
	@NotNull
    @Size(min = 6, max = 60)
	private String nome;
	
	@Column()
	@NotNull
    @Size(min = 6, max = 60)
	private String sobrenome;
	
	@Column()
	@NotNull
    @Email
	private String email;
	
	@Column()
	@CPF
	private String cpf;
	
	@Column()
    @NotNull
    @Size(min = 8)
	private String senha;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id", nullable = false)
	@JsonIgnore
	private Usuario usuario;

}
