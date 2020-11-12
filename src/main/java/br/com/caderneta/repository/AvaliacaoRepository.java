package br.com.caderneta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import br.com.caderneta.model.Avaliacao;
@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

}
