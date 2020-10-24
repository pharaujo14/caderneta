package br.com.caderneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.caderneta.model.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long>{

}
