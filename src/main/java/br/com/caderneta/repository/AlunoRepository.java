package br.com.caderneta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.caderneta.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	Aluno findAlunoByEmail(String email);
	Optional<Aluno> findByUsuario_id(Long id);
	
	@Query(value = "SELECT * FROM alunos WHERE usuario_id = :id"
			, nativeQuery = true)
	Long findByAlunoId(@Param("id") Long id);
	
	

}
