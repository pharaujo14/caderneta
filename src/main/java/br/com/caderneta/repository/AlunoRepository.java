package br.com.caderneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.caderneta.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
