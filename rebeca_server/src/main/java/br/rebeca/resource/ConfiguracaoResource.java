package br.rebeca.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.rebeca.model.Configuracao;
import br.rebeca.service.ConfiguracaoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/configuracoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConfiguracaoResource {

	@Autowired
	private ConfiguracaoService service;
	
    @ApiOperation(value = "Recupera uma configuração pelo seu identificador.")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id) {
		Configuracao obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

    @ApiOperation(value = "Insere uma configuração.")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Configuracao obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConfiguracao())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

    @ApiOperation(value = "Atualiza uma configuração. ")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Configuracao obj, @PathVariable Long id) {
    	
    	Configuracao dbobj = service.find(id);
    	
    	dbobj.setDsModulo(obj.getDsModulo());
    	dbobj.setCoProjeto(obj.getCoProjeto());
    	dbobj.setNoModulo(obj.getNoModulo());
    	dbobj.setNoObjetoBanco(obj.getNoObjetoBanco());
    	dbobj.setNoProprietarioBanco(obj.getNoProprietarioBanco());
    	
		obj.setIdConfiguracao(id);
		service.update(dbobj);
		return ResponseEntity.noContent().build();
	}

    @ApiOperation(value = "Deleta uma configuração.")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

    @ApiOperation(value = "Retorna uma lista de configurações.")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Configuracao>> findAll() {
		List<Configuracao> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}



}
