package br.rebeca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.rebeca.model.Configuracao;

@Repository
public interface DataSetRepository extends JpaRepository<Configuracao, Long> {


}
