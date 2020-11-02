package br.com.caderneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.caderneta.model.AlunoAula;

@Repository
public interface AlunoAulaRepository extends JpaRepository<AlunoAula, Long>{

}





