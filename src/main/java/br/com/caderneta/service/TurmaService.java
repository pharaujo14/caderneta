package br.com.caderneta.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.caderneta.exceptions.IdNotFoundException;
import br.com.caderneta.exceptions.IdNotNullException;
import br.com.caderneta.model.Aluno;
import br.com.caderneta.model.Professor;
import br.com.caderneta.model.Turma;
import br.com.caderneta.repository.AlunoRepository;
import br.com.caderneta.repository.TurmaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TurmaService {
	
	private final TurmaRepository turmaRepository;
	private final AlunoRepository alunoRepository;
	
	public Turma create(Turma turma) {
		turma.setId(null);
		return this.turmaRepository.save(turma);
	}
	
	public Turma update(Turma novo) throws IdNotFoundException, IdNotNullException {
		
		Turma antigo = this.findById(novo.getId());
		
		antigo.setNome(novo.getNome());
		antigo.setLocal(novo.getLocal());
		antigo.setHorarioInicio(novo.getHorarioInicio());
		antigo.setHorarioFim(novo.getHorarioFim());
//		antigo.setDias(novo.getDias());
		antigo.setInicio(novo.getInicio());
		antigo.setFim(novo.getFim());
			
		return this.turmaRepository.save(antigo);
	}
	
	public Turma findById(Long id) throws IdNotFoundException, IdNotNullException {
		Optional
				.ofNullable(id)
				.orElseThrow( () -> new IdNotNullException("turma: " + id));
		
		return this.turmaRepository.findById(id)
					.orElseThrow( () -> new IdNotFoundException("turma: " + id));
	}
	
	public List<Turma> findAll(){
		return this.turmaRepository.findAll();
	}
	
	public void deleteById(Long id) throws IdNotFoundException, IdNotNullException {
		this.findById(id);
		
		this.turmaRepository.deleteById(id);
	}
	
	public void addAluno(Long id, String email) throws IdNotFoundException, IdNotNullException {

		Aluno aluno = alunoRepository.findAlunoByEmail(email); 
		Turma turma = this.findById(id);
		
		if(aluno.getId().equals(null)) return; // criar exception e verificar se o aluno já está na lista

		Set<Aluno> alunos = turma.getAlunos();
		alunos.add(aluno);
		turma.setAlunos(alunos);

		this.turmaRepository.save(turma);

	}
	
	public List<Turma> findByProfessor(Professor professor){
		
		return this.turmaRepository.findByProfessor(professor);
		
	}

}