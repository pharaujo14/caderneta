package br.com.caderneta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caderneta.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	Aluno findAlunoByEmail(String email);
	Optional<Aluno> findByUsuario_id(Long id);

}
