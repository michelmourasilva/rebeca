package br.rebeca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.rebeca.model.Filtro;

@Repository
public interface FiltroRepository extends JpaRepository<Filtro, Long> {


}
