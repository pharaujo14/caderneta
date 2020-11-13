package br.com.caderneta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import br.com.caderneta.model.Chamada;

@Repository
public interface ChamadaRepository extends JpaRepository<Chamada, Long> {

	List<Chamada> findByAlunoId(Long alunoId); 
	
}
