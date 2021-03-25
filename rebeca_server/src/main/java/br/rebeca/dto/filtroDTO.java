package br.rebeca.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.rebeca.model.Filtro;
import br.rebeca.model.enums.TipoFiltro;

public class filtroDTO  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long idFiltro;
	

//    @NotEmpty(message="Preenchimento obrigatório.")
//    @Length(max=14, message="Atributo numérico deve ter no máximo 14 digitos")
	private long idConfiguracao;
	
//    @NotEmpty(message="Preenchimento obrigatório.")
//    @Length(max=300, message="O tamanho deve ser até 300 caracteres")
	private String noAtributo;
    
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
