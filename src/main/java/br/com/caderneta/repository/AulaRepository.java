package br.com.caderneta.repository;

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

}
