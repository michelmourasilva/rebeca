package br.rebeca.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.rebeca.model.Filtro;
import br.rebeca.model.enums.TipoFiltro;
import io.swagger.annotations.ApiModelProperty;

public class filtroDTO  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "Identificador gerado automaticamente pelo Oracle. Identificador que auxilia na identificação de um filtro que poderá ser utilizado dentro de uma configuração do serviço REST.")
	private long idFiltro;
	@ApiModelProperty(notes="Auxilia na identificação da configuração do serviço REST.")
	private long idConfiguracao;
	@ApiModelProperty(notes = "Nome do atributo que será utilizado como filtro")
	private String noAtributo;
    @ApiModelProperty(notes = "Tipo de operacao que podera ser aplicada em um campo especifico do objeto. Valores possiveis: IGUAL(0, “= :1”), DIFERENTE(1, “!= :1”), MAIOR(2, “> :1”),MENOR(3, “< :1”), MAIOROUIGUAL(4, “>= :1”), MENOROUIGUAL(5, “<= :1”), IN(6, “( select * from (TABLE(REBECA.fnc_string_virgula_tabela(:1))))”); Obs: Para operaćões utilizando IN, será utilizado uma funćão que irá quebrar as strings passadas no final do endpoint e transforma-las em uma colećão interável do Oracle. Ex; http://localhost:8080/data-set/all/PROJETO/MODULO/6/35228266,35274100,35442987 <<- Aonde o número 6 é o filtro criado para esse atributo")
	private Integer tipofiltro;
	
	@JsonIgnore
	public long getIdFiltro() {
		return idFiltro;
	}
	public void setIdFiltro(long idFiltro) {
		this.idFiltro = idFiltro;
	}
	public long getIdConfiguracao() {
		return idConfiguracao;
	}
	public void setIdConfiguracao(long idConfiguracao) {
		this.idConfiguracao = idConfiguracao;
	}
	public String getNoAtributo() {
		return noAtributo;
	}
	public void setNoAtributo(String noAtributo) {
		this.noAtributo = noAtributo;
	}
	

    public TipoFiltro getTipoFiltro() {
    	return TipoFiltro.toEnum(tipofiltro);
    }
    
    public void setTipoFiltro(TipoFiltro tipofiltro) {
    	this.tipofiltro = tipofiltro.getCodTipo();
    }
	
	public filtroDTO() {
		super();
	}
	
	public filtroDTO(Filtro obj) {
		super();
		this.idFiltro = obj.getIdFiltro();
		this.noAtributo = obj.getNoAtributo();
	}
	
}
