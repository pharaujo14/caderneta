package request;

import java.util.List;

import br.com.caderneta.model.Turma;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AulasRequest {
	
	private List<Integer> dias;
	
	private Turma turma;
	
	

}
