package br.com.caderneta.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.caderneta.model.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long>{
	
	List<Aula> findByTurma_id(Long id);
	
	@Modifying()
    @Transactional
	@Query(value = "DELETE FROM aulas WHERE turma_id = :id"
			, nativeQuery = true)
	void deleteByTurma(@Param("id") Long id);
	
	@Query(value = "select * from aulas where turma_id in "
			+ "(select id from turmas where professor_id = :id) order by data, horario_inicio"
			, nativeQuery = true)
	List<Aula> findByProfessor(@Param("id") Long id);
	
	@Query(value = "select * from aulas where data between :date and '2030-01-01' and turma_id in "
			+ "(select id from turmas where professor_id = :id) order by data, horario_inicio"
			, nativeQuery = true)
	List<Aula> findByProfessorDashboard(@Param("id") Long id, @Param("date") LocalDate date);
	
	@Query(value = "select * from aulas where turma_id in "
			+ "(SELECT id FROM turmas t INNER JOIN alunos_turmas at ON t.id = at.turma_id "
			+ "WHERE at.aluno_id = :id) order by data, horario_inicio", nativeQuery = true)
	List<Aula> findByAluno(@Param("id") Long id);
	
	@Query(value = "select * from aulas where data between :date and '2030-01-01' and turma_id in "
			+ "(SELECT id FROM turmas t INNER JOIN alunos_turmas at ON t.id = at.turma_id "
			+ "WHERE at.aluno_id = :id) order by data, horario_inicio", nativeQuery = true)
	List<Aula> findByAlunoDashboard(@Param("id") Long id, @Param("date") LocalDate date);

}
