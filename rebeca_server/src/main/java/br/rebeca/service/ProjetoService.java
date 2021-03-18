package br.rebeca.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.rebeca.dto.ProjetoDTO;
import br.rebeca.model.Projeto;
import br.rebeca.repository.ProjetoRepository;
import br.rebeca.service.exceptions.DataIntegrityException;
import br.rebeca.service.exceptions.ObjectNotFoundException;

@Service
public class ProjetoService {

	@Autowired
	private ProjetoRepository repo;

	@PersistenceContext
	EntityManager entityManager;

	public Projeto find(Long id) {
		Optional<Projeto> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: " + Projeto.class.getName()));
	}
	
	
	public Projeto insert(Projeto obj) {
		obj.setIdProjeto(0);
		return repo.save(obj);
	}

	public Projeto update(Projeto obj) {
		find(obj.getIdProjeto());
		return repo.save(obj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao é possível excluir um registro com filhos.");
		}
	}

	public List<Projeto> findAll() {
		return repo.findAll();
	}

	public Page<Projeto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	
	public Projeto fromDTO(ProjetoDTO objDto) {
		return new Projeto(objDto.getIdProjeto(), objDto.getNoProjeto(), objDto.getDsProjeto());
	}
	

}
