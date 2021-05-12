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

import br.rebeca.dto.ConfiguracaoDTO;
import br.rebeca.model.Configuracao;
import br.rebeca.model.Projeto;
import br.rebeca.service.ConfiguracaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/configuracoes", produces = MediaType.APPLICATION_JSON_VALUE) 
@Api(value="configurações", description="Operações referente as configurações dos endpoints", tags="Configurações")
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
	public ResponseEntity<Long> insert(@Valid @RequestBody ConfiguracaoDTO objDto) {
    	
    	Projeto projeto = new Projeto();
    	
    	projeto.setIdProjeto(objDto.getIdProjeto());
    	
    	Configuracao obj = service.fromDTO(objDto);
    	
    	long idConfiguracao = service.insert(obj);
    	
		//URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConfiguracao())
		//		.toUri();
		return  ResponseEntity.ok().body(idConfiguracao);
	}

    @ApiOperation(value = "Atualiza uma configuração. ")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ConfiguracaoDTO objDto, @PathVariable("id") Long id) {
    	
    	Configuracao obj = service.fromDTO(objDto);
    	obj.setIdConfiguracao(id);

    	Projeto projeto = new Projeto();
    	projeto.setIdProjeto(objDto.getIdProjeto());
    	
    	obj.setDsModulo(obj.getDsModulo());

    	obj.setNoModulo(obj.getNoModulo());
    	obj.setNoObjetoBanco(obj.getNoObjetoBanco());
    	obj.setNoProprietarioBanco(obj.getNoProprietarioBanco());
    	
		service.update(obj);
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

    
    @ApiOperation(value = "Recupera todas as configurações de um projeto.")
	@RequestMapping(value = "/projeto/{idProjeto}", method = RequestMethod.GET)
	public ResponseEntity<List<Configuracao>> findAllByProjetoIdProjeto(@PathVariable Long idProjeto) {
    	List<Configuracao> list = service.findAllByProjetoIdProjeto(idProjeto);
		return ResponseEntity.ok().body(list);
	}


}
