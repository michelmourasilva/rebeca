package br.rebeca.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Entity
@Immutable
@Table(name = "VW_END_POINT", schema = "REBECA")
public class EndPoint implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@Column(name="ID")
	private long rowid;
	@ApiModelProperty(notes = "Exemplo de como está sedo gerado um endpoint para disponibilização de dados. Se baseia na união de várias informações das outras tabelas.")
	private String endPoint;
	@ApiModelProperty(notes = "Caso um endpoint possua um filtro em um campo específico, será apresentado a regra correspondente para o símbolo “?” apresentado no campo END_POINT.")
	private String atributoFiltro;
	@ApiModelProperty(notes = "Nome do projeto que está disponibilizando os dados.")
	private String noProjeto;
	@ApiModelProperty(notes = "Nome do módulo deste sistema.")
	private String noModulo;
	@ApiModelProperty(notes = "Breve descrição do módulo de um sistema.")
	private String dsModulo;
	
    @Basic
    @Column(name = "ENDPOINT")
    @ApiModelProperty(value = "endPoint")
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	
    @Basic
    @Column(name = "ATRIBUTO_FILTRO")
    @ApiModelProperty(value = "atributoFiltro")
	public String getAtributoFiltro() {
		return atributoFiltro;
	}
	public void setAtributoFiltro(String atributoFiltro) {
		this.atributoFiltro = atributoFiltro;
	}
	
    @Basic
    @Column(name = "NO_PROJETO")
    @ApiModelProperty(value = "noProjeto")
	public String getNoProjeto() {
		return noProjeto;
	}
	public void setNoProjeto(String noProjeto) {
		this.noProjeto = noProjeto;
	}
	
    @Basic
    @Column(name = "NO_MODULO")
    @ApiModelProperty(value = "noModulo")
	public String getNoModulo() {
		return noModulo;
	}
	public void setNoModulo(String noModulo) {
		this.noModulo = noModulo;
	}
	
    @Basic
    @Column(name = "DS_MODULO")
    @ApiModelProperty(value = "dsModulo")
	public String getDsModulo() {
		return dsModulo;
	}
	public void setDsModulo(String dsModulo) {
		this.dsModulo = dsModulo;
	}
	
	public EndPoint() {
		super();		
	}
	
	public EndPoint(String endPoint, String atributoFiltro, String noProjeto, String noModulo, String dsModulo) {
		super();
		this.endPoint = endPoint;
		this.atributoFiltro = atributoFiltro;
		this.noProjeto = noProjeto;
		this.noModulo = noModulo;
		this.dsModulo = dsModulo;
	}
	
	
	
}
