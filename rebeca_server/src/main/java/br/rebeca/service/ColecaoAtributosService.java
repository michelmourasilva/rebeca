package br.rebeca.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.rebeca.model.ColecaoAtributos;
import br.rebeca.repository.ColecaoAtributosRepository;

@Service
public class ColecaoAtributosService {

	@Autowired
	private ColecaoAtributosRepository repo;

	@PersistenceContext
	EntityManager entityManager;

	public List<ColecaoAtributos> findBynoObjeto(String noObjeto) {
		return repo.findBynoObjeto(noObjeto);
	}
	

	public ColecaoAtributos findByNoObjetoAndNoColuna(String noObjeto, String noColuna) {
		return repo.findByNoObjetoAndNoColuna(noObjeto, noColuna);
	}
	
	
	
	public List<ColecaoAtributos> findAll() {
		return repo.findAll();
	}

	
	public List<String> listallObjetos() {
		return repo.listallObjetos();
	}
}
