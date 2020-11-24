package br.com.caderneta.model.dto.create;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class TurmaCreateDTO {
	
	@NotNull
    @Size(min = 6, max = 60)
	private String nome;	
	
	private Long professorId;
	
	@NotNull
    @Size(min = 6, max = 60)
	private String local;
	
	@NotNull
	private String horarioInicio;
	
	@NotNull
	private String horarioFim;
	
	@NotNull
	private LocalDate inicio;
	
	@NotNull
	private LocalDate fim;
	
	@NotNull
	private List<Integer> dias;

}
