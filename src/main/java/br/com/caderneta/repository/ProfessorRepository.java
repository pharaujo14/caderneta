package br.com.caderneta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caderneta.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	
	Optional<Professor> findByUsuario_id(Long id);

}
