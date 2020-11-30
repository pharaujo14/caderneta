package br.com.caderneta.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.caderneta.exceptions.IdNotFoundException;
import br.com.caderneta.exceptions.IdNotNullException;
import br.com.caderneta.model.Aula;
import br.com.caderneta.model.Professor;
import br.com.caderneta.model.Turma;
import br.com.caderneta.model.Usuario;
import br.com.caderneta.repository.AulaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AulaService {

	private final AulaRepository aulaRepository;
	private final ProfessorService professorService;
	private final AlunoService alunoService;

	public Aula create(Aula aula) {

		aula.setId(null);
		return this.aulaRepository.save(aula);
	}

	public void createAulas(List<Integer> dias, Turma turma) {

		List<Aula> aulas = new ArrayList<>();

		LocalDate dataInicio = turma.getInicio();

		LocalDate dataFim = turma.getFim();

		for (Integer dia : dias) {

			List<LocalDate> datas = dataInicio.datesUntil(dataFim)
					.filter(data -> dia.equals(data.getDayOfWeek().getValue())).collect(Collectors.toList());

			for (LocalDate data : datas) {

				aulas.add(Aula.builder().data(data).turma(turma)
						.horarioInicio(turma.getHorarioInicio())
						.horarioFim(turma.getHorarioFim())
						.nome("Aula dia " + data.getDayOfMonth() + "-" + data.getMonthValue()).build());
			}

		}

		aulas.sort((a1, a2) -> a1.getData().compareTo(a2.getData()));

		this.aulaRepository.saveAll(aulas);

	}

	public Aula update(Aula nova) throws IdNotNullException, IdNotFoundException {

		Aula antiga = this.findById(nova.getId());

		antiga.setNome(nova.getNome());
		antiga.setData(nova.getData());

		return this.aulaRepository.save(antiga);
	}

	public Aula findById(Long id) throws IdNotNullException, IdNotFoundException {
		Optional.ofNullable(id).orElseThrow(() -> new IdNotNullException("aula: " + id));

		return this.aulaRepository.findById(id).orElseThrow(() -> new IdNotFoundException("aula: " + id));

	}

	public List<Aula> findAll() {
		return this.aulaRepository.findAll();
	}
	
	public List<Aula> findByTurma_id(Long id) {
		return this.aulaRepository.findByTurma_id(id);

	}
	
	public List<Aula> findByProfessor() {
		Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Professor professor = this.professorService.findByUsuario(user.getId());
		
		Long id = professor.getId();
		
		return this.aulaRepository.findByProfessor(id);

	}
	
	public List<Aula> findByAluno() {
		Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long userId = user.getId();
		
		Long id = this.alunoService.findByUsuarioId(userId);
		
		return this.aulaRepository.findByAluno(id);

	}

	public void deleteById(Long id) throws IdNotNullException, IdNotFoundException {
		this.findById(id);

		this.aulaRepository.deleteById(id);
	}
	
	@Transactional
	public void deleteByTurma(Long id){
		this.aulaRepository.deleteByTurma(id);
	}

}
