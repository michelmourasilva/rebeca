package br.rebeca.resource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;

import br.rebeca.model.ColecaoAtributos;
import br.rebeca.service.ColecaoAtributosService;
import br.rebeca.service.DataSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/dataset")
@Api(value="Conjunto de dados", description="Conjunto de dados de um determinado projeto", tags="Conjunto de dados")
public class DataSetResource {

	@Autowired
	private DataSetService service;
	
	@Autowired
	private ColecaoAtributosService serviceColecao;
	
	

    @ApiOperation(value = "Retorna os dados a partir de uma configuração, sistema e módulo. Pode também ser incluso alguns critérios pré definidos.")
	@RequestMapping(value = {"/{projeto}/{modulo}","/{projeto}/{modulo}/{filtro}/{criterios}"}, method = RequestMethod.GET, produces={"application/json"})
	public @JsonRawValue @JsonValue @ResponseBody String getTudo(@PathVariable String projeto,
			@PathVariable String modulo, @PathVariable(value="filtro", required=false) String filtros, @PathVariable(value="criterios", required=false) String criterios) throws SQLException, IOException {
		return service.getAllJson(projeto, modulo,filtros,criterios );
	}
	
    
    @ApiOperation(value = "Retorna a lista de objetos e colunas que podem ser utilizados na configuração de um projeto.")
  	@RequestMapping(value = "/colecao/atributos", method = RequestMethod.GET)
  	public ResponseEntity<List<ColecaoAtributos>> findAll() {
  		List<ColecaoAtributos> list = serviceColecao.findAll();
  		return ResponseEntity.ok().body(list);
  	}
    
    
    @ApiOperation(value = "Retorna os objetos disponíveis para utilização na configuração de um projeto")
  	@RequestMapping(value = "/colecao/objetos", method = RequestMethod.GET)
  	public ResponseEntity<List<String>> listallObjetos() {
  		List<String> list = serviceColecao.listallObjetos();
  		return ResponseEntity.ok().body(list);
  	}
     
    @ApiOperation(value = "Retorna os atributos disponíveis de um objeto específico para utilização na configuração de um projeto")
  	@RequestMapping(value = "/colecao/atributos/{noObjeto}", method = RequestMethod.GET)
  	public ResponseEntity<List<ColecaoAtributos>> findBynoObjeto(@PathVariable String noObjeto) {
  		List<ColecaoAtributos> list = serviceColecao.findBynoObjeto(noObjeto);
  		return ResponseEntity.ok().body(list);
  	}
      
    
    @ApiOperation(value = "Retorna um atributo de um objeto específico de para utilização na configuração de um projeto")
  	@RequestMapping(value = "/colecao/atributos/{noObjeto}/{noColuna}", method = RequestMethod.GET)
  	public ResponseEntity<ColecaoAtributos> findByNoObjetoAndNoColuna(@PathVariable String noObjeto, @PathVariable String noColuna) {
  		ColecaoAtributos atributo = serviceColecao.findByNoObjetoAndNoColuna(noObjeto, noColuna);
  		return ResponseEntity.ok().body(atributo);
  	}
   
}
