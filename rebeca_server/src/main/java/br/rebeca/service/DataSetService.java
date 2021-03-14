package br.rebeca.service;

import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.rebeca.repository.DataSetRepository;

@Service
public class DataSetService {
	
	@Autowired
	private DataSetRepository repo;

	@PersistenceContext
	EntityManager entityManager;
	
	public String getAllJson(String sistema, String modulo, String filtro, String criterios)
			throws SQLException, IOException {

		StoredProcedureQuery query = (entityManager).createStoredProcedureQuery("corp.prc_recupera_configuracao_serv");
		query.registerStoredProcedureParameter("p_sistema", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_modulo", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_id_filtro",  String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_criterio_pesquisa", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("p_json_string", Clob.class, ParameterMode.OUT);
		query.setParameter("p_sistema", sistema.toUpperCase());
		query.setParameter("p_modulo", modulo.toUpperCase());

		query.setParameter("p_id_filtro", (filtro != null ? filtro : "") );
		query.setParameter("p_criterio_pesquisa", (criterios != null ? criterios : "") );

		query.execute();
		Clob clob = (Clob) query.getOutputParameterValue("p_json_string");

		return read(clob);

	}

	private static String read(Clob c) throws SQLException, IOException {
		StringBuilder sb = new StringBuilder((int) c.length());
		Reader r = c.getCharacterStream();
		char[] cbuf = new char[2048];
		int n;
		while ((n = r.read(cbuf, 0, cbuf.length)) != -1) {
			sb.append(cbuf, 0, n);
		}
		return sb.toString();
	}

}
