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

import br.rebeca.model.Configuracao;
import br.rebeca.repository.ConfiguracaoRepository;
import br.rebeca.service.exceptions.DataIntegrityException;
import br.rebeca.service.exceptions.ObjectNotFoundException;


@Service
public class ConfiguracaoService {

	@Autowired
	private ConfiguracaoRepository configuracaoRepository;


	@PersistenceContext
	EntityManager entityManager;

	public Configuracao find(Long id) {
		Optional<Configuracao> obj = configuracaoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id:" + id + ", Tipo: " + Configuracao.class.getName()));
	}

	public Configuracao insert(Configuracao obj) {
		obj.setIdConfiguracao(0);
		return configuracaoRepository.save(obj);
	}

	public Configuracao update(Configuracao obj) {
		find(obj.getIdConfiguracao());
		return configuracaoRepository.save(obj);
	}

	public void delete(Long id) {
		find(id);
		try {
			configuracaoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao é possível excluir um registro com filhos.");
		}
	}

	public List<Configuracao> findAll() {
		return configuracaoRepository.findAll();
	}

	public Page<Configuracao> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return configuracaoRepository.findAll(pageRequest);
	}



}
