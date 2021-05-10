package br.rebeca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.rebeca.model.ColecaoAtributos;

@Repository
public interface ColecaoAtributosRepository extends JpaRepository<ColecaoAtributos, Long> {

	List<ColecaoAtributos> findBynoObjeto(String noObjeto);

	@Query("SELECT DISTINCT noObjeto FROM ColecaoAtributos")
	List<String> listallObjetos();
	
}
