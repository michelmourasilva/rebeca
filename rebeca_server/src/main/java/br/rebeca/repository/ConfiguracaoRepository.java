package br.rebeca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.rebeca.model.ColecaoAtributos;
import br.rebeca.model.Configuracao;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {



	List<Configuracao> findAllByProjetoIdProjeto(Long idProjeto);
	
}
