package br.rebeca.resource;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;

import br.rebeca.service.DataSetService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/dataset")
public class DataSetResource {

	@Autowired
	private DataSetService service;
	

    @ApiOperation(value = "Retorna os dados a partir de uma configuração, sistema e módulo. Pode também ser incluso alguns critérios pré definidos.")
	@RequestMapping(value = {"/{projeto}/{modulo}","/{projeto}/{modulo}/{filtro}/{criterios}"}, method = RequestMethod.GET)
	public @JsonRawValue @JsonValue @ResponseBody String getTudo(@PathVariable String projeto,
			@PathVariable String modulo, @PathVariable(value="filtro", required=false) String filtros, @PathVariable(value="criterios", required=false) String criterios) throws SQLException, IOException {
		return service.getAllJson(projeto, modulo,filtros,criterios );
	}
	
}
