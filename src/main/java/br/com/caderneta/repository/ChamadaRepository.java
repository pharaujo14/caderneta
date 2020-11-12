package br.com.caderneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import br.com.caderneta.model.Chamada;

@Repository
public interface ChamadaRepository extends JpaRepository<Chamada, Long> {

}
