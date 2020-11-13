package br.com.caderneta.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caderneta.exceptions.IdNotFoundException;
import br.com.caderneta.exceptions.IdNotNullException;
import br.com.caderneta.model.Professor;
import br.com.caderneta.repository.ProfessorRepository;

public class ProfessorServiceTest {

	@Mock
	private ProfessorRepository professorRepository;
	private ProfessorService professorService;
	private Professor professor;
	private Professor profNovo;

	@BeforeEach
	public void inits() {
		MockitoAnnotations.initMocks(this);
		this.professorService = new ProfessorService(professorRepository, null);

		professor = Professor.builder().nome("TesteTeste").sobrenome("TesteTeste").email("teste@teste.com")
				.cpf("09102684012").id(1l).usuario(null).build();

	}

	@Test
	public void testaFindByIdSucess() throws IdNotFoundException, IdNotNullException {

		when(this.professorRepository.findById(any())).thenReturn(Optional.of(professor));

		Professor professorRetorno = professorService.findById(1l);

		assertEquals(professor, professorRetorno);
	}

	@Test
	public void testaFindByIdFalhaNulo() {
		assertThrows(IdNotNullException.class, () -> professorService.findById(null));
	}

	@Test
	public void testaFindByIdFalhaIdNaoEncontrado() {

		when(this.professorRepository.findById(any())).thenReturn(Optional.ofNullable(null));

		assertThrows(IdNotFoundException.class, () -> professorService.findById(1l));
	}

//	public List<Professor> findAll(){
//		return this.professorRepository.findAll();
//	}
//	
//	public void deleteById(Long id) throws IdNotFoundException, IdNotNullException {
//		this.findById(id);
//		
//		this.professorRepository.deleteById(id);
//	}

	@Test
	public void testaUpdateSuccesso() {				
		professor.setNome("Teste1234");
		profNovo = professorRepository.save(professor);

		assertNotNull(professor.getId());
		assertThat(profNovo.getId(), equalTo(professor.getId()));
		assertThat(profNovo.getNome(), equalTo("Teste1234"));

	}


//	public Professor fromCreate(Professor professor) {
//		
//		return Professor.builder()
//				.nome(professor.getNome())
//				.sobrenome(professor.getSobrenome())
//				.email(professor.getEmail())
//				.cpf(professor.getCpf())
//				.senha(pEnconder.encode(professor.getSenha())) // ver isso com o Gustavo
//				.usuario(
//						Usuario.builder()
//						.senha(pEnconder.encode(professor.getSenha()))
//						.username(professor.getEmail())
//						.role(RoleEnum.PROFESSOR)
//						.build())
//				.build();
//				
//	}

}
