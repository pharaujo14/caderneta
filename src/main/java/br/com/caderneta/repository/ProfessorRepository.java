package br.com.caderneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caderneta.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{

}
