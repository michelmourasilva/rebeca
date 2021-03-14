package br.rebeca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.rebeca.model.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {


}
