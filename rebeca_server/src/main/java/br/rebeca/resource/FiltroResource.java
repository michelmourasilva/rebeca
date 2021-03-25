package br.rebeca.resource;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.rebeca.dto.filtroDTO;
import br.rebeca.model.Configuracao;
import br.rebeca.model.Filtro;
import br.rebeca.service.FiltroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/filtros")
@Api(value="Filtros", description="Operações referente aos filtros de um endpoint", tags="Filtros")
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

    
    @ApiOperation(value = "Insere um filtro")
   	@RequestMapping(method = RequestMethod.POST)
   	public ResponseEntity<Void> insert(@Valid @RequestBody filtroDTO objDto) {
    	
    	
    	Configuracao configuracao = new Configuracao();
    	
    	configuracao.setIdConfiguracao(objDto.getIdConfiguracao());
    	
    	Filtro obj = service.fromDTO(objDto);
    	
   		obj = service.insert(obj);
   		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdFiltro())
   				.toUri();
   		return ResponseEntity.created(uri).build();
   	}

    @ApiOperation(value = "Atualiza um filtro.")
   	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   	public ResponseEntity<Void> update(@Valid @RequestBody filtroDTO objDto, @PathVariable("id") Long id) {
       	

    	Filtro obj = service.fromDTO(objDto);
    	obj.setIdFiltro(id);
    	
    	Configuracao configuracao = new Configuracao();
    	configuracao.setIdConfiguracao(objDto.getIdConfiguracao());
    	
    	obj.setNoAtributo(obj.getNoAtributo());
   		
   		service.update(obj);
   		return ResponseEntity.noContent().build();
   	}

       @ApiOperation(value = "Deleta um filtro")
   	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   	public ResponseEntity<Void> delete(@PathVariable Long id) {
       	
   		service.delete(id);
   		return ResponseEntity.noContent().build();
   	}
           
    
    
    
	
}
