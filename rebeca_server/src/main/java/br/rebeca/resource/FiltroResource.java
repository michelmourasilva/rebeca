package br.rebeca.resource;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.rebeca.model.Filtro;
import br.rebeca.service.FiltroService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/filtros")
public class FiltroResource {

	@Autowired
	private FiltroService service;


    @ApiOperation(value = "Retorna um filtro")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> find(@PathVariable Long id) {
		Filtro obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
    @ApiOperation(value = "Retorna uma lista de filtros")
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<Filtro>> findAll() {
		List<Filtro> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	
}
