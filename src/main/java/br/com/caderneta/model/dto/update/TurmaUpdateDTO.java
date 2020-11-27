package br.com.caderneta.model.dto.update;

import javax.validation.constraints.NotNull;

import br.com.caderneta.model.Aluno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurmaUpdateDTO {
	
	@NotNull
	private Long id;
	
	@NotNull
	private Aluno aluno;

}
