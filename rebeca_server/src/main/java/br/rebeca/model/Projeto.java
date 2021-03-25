package br.rebeca.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Entity
@Table(name = "TB_PROJETO", schema = "REBECA", uniqueConstraints={
	    @UniqueConstraint(columnNames = {"NO_PROJETO"} )})
public class Projeto implements Serializable{

	private static final long serialVersionUID = 1L;
    

	private long idProjeto;
    private String noProjeto;
    private String dsProjeto;

    private Set<Configuracao> configuracoes;
    
    
    public Projeto() {
    	super();
    }
    

	public Projeto(long idProjeto, String noProjeto, String dsProjeto) {
		super();
		this.idProjeto = idProjeto;
		this.noProjeto = noProjeto;
		this.dsProjeto = dsProjeto;
	}


	@Id
    @Basic
    @Column(name = "ID_PROJETO")
    @ApiModelProperty(value = "id", hidden  = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(long idProjeto) {
		this.idProjeto = idProjeto;
	}

    @Basic
    @ApiModelProperty(value = "noProjeto", required = true)
    @Column(name = "NO_PROJETO", nullable = false, unique = true)
	public String getNoProjeto() {
		return noProjeto;
	}

	public void setNoProjeto(String noProjeto) {
		this.noProjeto = noProjeto;
	}

    @Basic
    @ApiModelProperty(value = "dsProjeto", required = true)
    @Column(name = "DS_PROJETO", nullable = false)
	public String getDsProjeto() {
		return dsProjeto;
	}

	public void setDsProjeto(String dsProjeto) {
		this.dsProjeto = dsProjeto;
	}

	@JsonIgnore
    @OneToMany(mappedBy="projeto", fetch = FetchType.LAZY)
	public Set<Configuracao> getConfiguracoes() {
		return configuracoes;
	}


	public void setConfiguracoes(Set<Configuracao> configuracoes) {
		this.configuracoes = configuracoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idProjeto ^ (idProjeto >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		if (idProjeto != other.idProjeto)
			return false;
		return true;
	}

    
    

    
    
}
