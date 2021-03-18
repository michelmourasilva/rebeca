package br.rebeca.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.rebeca.dto.ProjetoDTO;
import br.rebeca.model.Projeto;
import br.rebeca.service.ProjetoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/projetos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjetoResource {

	@Autowired
	private ProjetoService service;

	@ApiOperation(value = "Recupera um projeto")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id) {
		Projeto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
    
    @ApiOperation(value = "Insere um projeto")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ProjetoDTO objDto) {
    	
    	Projeto obj = service.fromDTO(objDto);
    	obj = service.insert(obj);
    	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdProjeto())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

    @ApiOperation(value = "Atualiza um projeto")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ProjetoDTO objDto, @PathVariable("id") Long id) {
    	
    	Projeto obj = service.fromDTO(objDto);
    	obj.setIdProjeto(id);
    
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

    @ApiOperation(value = "Deleta um projeto")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
    	
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
    
    
    @ApiOperation(value = "Retorna uma lista de projetos")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Projeto>> findAll() {
		List<Projeto> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}


	
}
