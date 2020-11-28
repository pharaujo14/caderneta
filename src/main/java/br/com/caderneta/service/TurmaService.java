package br.com.caderneta.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.caderneta.exceptions.IdNotFoundException;
import br.com.caderneta.exceptions.IdNotNullException;
import br.com.caderneta.model.Aluno;
import br.com.caderneta.model.Professor;
import br.com.caderneta.model.Turma;
import br.com.caderneta.model.Usuario;
import br.com.caderneta.model.dto.create.TurmaCreateDTO;
import br.com.caderneta.model.dto.update.TurmaUpdateDTO;
import br.com.caderneta.repository.AlunoRepository;
import br.com.caderneta.repository.TurmaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TurmaService {

	private final AulaService aulaService;
	private final TurmaRepository turmaRepository;
	private final AlunoRepository alunoRepository;
	private final ProfessorService professorService;
	private final AlunoService alunoService;

	public Turma create(TurmaCreateDTO turmaDTO) {
		Turma turma = fromCreateDTO(turmaDTO);
		return this.turmaRepository.save(turma);
	}

	public Turma update(Turma novo) throws IdNotFoundException, IdNotNullException {

		Turma antigo = this.findById(novo.getId());

		antigo.setNome(novo.getNome());
		antigo.setLocal(novo.getLocal());
		antigo.setHorarioInicio(novo.getHorarioInicio());
		antigo.setHorarioFim(novo.getHorarioFim());
		antigo.setInicio(novo.getInicio());
		antigo.setFim(novo.getFim());

		return this.turmaRepository.save(antigo);
	}
	
	public void addAluno(TurmaUpdateDTO turmaUpdate) {
		
		Turma antiga = this.findById(turmaUpdate.getId());		
		Aluno aluno = alunoRepository.findAlunoByEmail(turmaUpdate.getEmailAluno());
		
		if (aluno.getId().equals(null))
			return; // criar exception e verificar se o aluno já está na lista
		
		Set<Aluno> alunos = antiga.getAlunos();
		alunos.add(aluno);
		antiga.setAlunos(alunos);
		
		this.turmaRepository.save(antiga);

	}
	
	public void deleteAluno(TurmaUpdateDTO turmaUpdate) {
		
		Turma antiga = this.findById(turmaUpdate.getId());		
		Aluno aluno = alunoRepository.findAlunoByEmail(turmaUpdate.getEmailAluno());
		
		if (aluno.getId().equals(null))
			return; // criar exception e verificar se o aluno já está na lista
		
		Set<Aluno> alunos = antiga.getAlunos();
		alunos.remove(aluno);
		antiga.setAlunos(alunos);
		
		this.turmaRepository.save(antiga);

	}

	public Turma findById(Long id) throws IdNotFoundException, IdNotNullException {
		Optional.ofNullable(id).orElseThrow(() -> new IdNotNullException("turma: " + id));

		return this.turmaRepository.findById(id).orElseThrow(() -> new IdNotFoundException("turma: " + id));
	}

	public List<Turma> findAll() {
		return this.turmaRepository.findAll();
	}

	public void deleteById(Long id) throws IdNotFoundException, IdNotNullException {
		this.findById(id);

		this.turmaRepository.deleteById(id);
	}


	public List<Turma> findByProfessor() {
		Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Professor professor = this.professorService.findByUsuario(user.getId());

		return this.turmaRepository.findByProfessor(professor);

	}
	
	public List<Turma> findByAluno() {
		Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long userId = user.getId();
	
		Long id = this.alunoService.findByUsuarioId(userId);

		return this.turmaRepository.findByAlunoId(id);

	}
	
	private Turma fromCreateDTO(TurmaCreateDTO turmaDTO) {
		Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Professor professor = this.professorService.findByUsuario(user.getId());
		
		Turma turma = Turma.builder()
				.nome(turmaDTO.getNome())
				.professor(professor)
				.local(turmaDTO.getLocal())
				.horarioInicio(turmaDTO.getHorarioInicio())
				.horarioFim(turmaDTO.getHorarioFim())
				.inicio(turmaDTO.getInicio())
				.fim(turmaDTO.getFim())
				.build();
		
		aulaService.createAulas(turmaDTO.getDias(), turma);
		
		return turma;

	}
	


}