package br.rebeca.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.rebeca.model.EndPoint;
import br.rebeca.repository.EndPointRepository;


@Service
public class EndPointService {

	@Autowired
	private EndPointRepository endPointRepository;

	@PersistenceContext
	EntityManager entityManager;

	public List<EndPoint> findAll() {
		return endPointRepository.findAll();
	}



}
