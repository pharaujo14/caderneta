package br.com.caderneta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caderneta.model.Professor;
import br.com.caderneta.model.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long>{
	
	List<Turma> findByProfessor(Professor professor);

}




