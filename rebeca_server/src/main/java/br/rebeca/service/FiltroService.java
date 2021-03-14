package br.rebeca.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.rebeca.model.Filtro;
import br.rebeca.repository.FiltroRepository;
import br.rebeca.service.exceptions.ObjectNotFoundException;

@Service
public class FiltroService {

	@Autowired
	private FiltroRepository repo;

	@PersistenceContext
	EntityManager entityManager;

	public Filtro find(Long id) {
		Optional<Filtro> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: " + Filtro.class.getName()));
	}
	

	public List<Filtro> findAll() {
		return repo.findAll();
	}

	public Page<Filtro> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	

}
