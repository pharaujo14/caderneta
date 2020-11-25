package br.com.caderneta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.caderneta.model.Professor;
import br.com.caderneta.model.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long>{
	
	List<Turma> findByProfessor(Professor professor);	
	
	
	@Query(value = "SELECT * FROM turmas t INNER JOIN alunos_turmas at ON t.id = at.turma_id WHERE at.aluno_id = :id"
			, nativeQuery = true)
	List<Turma> findByAlunoId(@Param("id") Long id);

}



