package br.rebeca.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.rebeca.model.EndPoint;
import br.rebeca.service.EndPointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/endpoints", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value="Endpoint", description="Relação das interfaces disponíveis para consumo de dados", tags="Endpoint")
public class  EndPointResource {

	@Autowired
	private EndPointService service;
	
    @ApiOperation(value = "Retorna a lista dos endpoints disponíveis para uso.")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EndPoint>> findAll() {
		List<EndPoint> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
}
