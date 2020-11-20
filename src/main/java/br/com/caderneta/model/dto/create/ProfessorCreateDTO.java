package br.com.caderneta.model.dto.create;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class ProfessorCreateDTO {
	
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	@Getter
	private Long id;
	
	@NotNull
    @Size(min = 6, max = 60)
	private String nome;
	
	@NotNull
    @Size(min = 6, max = 60)
	private String sobrenome;
	
	@NotNull
    @Email
	private String email;
	
	@CPF
	private String cpf;
	
    @NotNull
    @Size(min = 8)
	private String senha;

}
