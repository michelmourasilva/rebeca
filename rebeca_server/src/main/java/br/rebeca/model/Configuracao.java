package br.rebeca.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "TB_CONFIGURACAO_SERVICO", schema = "REBECA", catalog = "",uniqueConstraints={
	    @UniqueConstraint(columnNames = {"ID_PROJETO", "NO_MODULO","NO_OBJETO_BANCO","NO_PROPRIETARIO_BANCO"} )})
public class Configuracao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private long idConfiguracao;
    private long idProjeto;
    private String noModulo;
    private String dsModulo;
    private String noObjetoBanco;
    private String noProprietarioBanco;
    
    
    @Id
    @Column(name = "ID_CONFIGURACAO_SERVICO")
    @ApiModelProperty(value = "id", hidden=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getIdConfiguracao() {
        return idConfiguracao;
    }

    public void setIdConfiguracao(long idConfiguracao) {
        this.idConfiguracao = idConfiguracao;
    }

    @Basic
    @Column(name = "ID_PROJETO", nullable = false)
    @ApiModelProperty(value = "idProjeto", required = true)
    public long getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(long idProjeto) {
        this.idProjeto = idProjeto;
    }

    @Basic
    @Column(name = "NO_MODULO", nullable = false)
    @ApiModelProperty(value = "noModulo", required = true)
    public String getNoModulo() {
        return noModulo;
    }

    public void setNoModulo(String noModulo) {
        this.noModulo = noModulo;
    }

    @Basic
    @Column(name = "DS_MODULO", nullable = false)
    @ApiModelProperty(value = "dsModulo", required = true)
    public String getDsModulo() {
        return dsModulo;
    }

    public void setDsModulo(String dsModulo) {
        this.dsModulo = dsModulo;
    }

    @Basic
    @Column(name = "NO_OBJETO_BANCO",nullable = false)
    @ApiModelProperty(value = "noObjetoBanco", required = true)
    public String getNoObjetoBanco() {
        return noObjetoBanco;
    }

    public void setNoObjetoBanco(String noObjetoBanco) {
        this.noObjetoBanco = noObjetoBanco;
    }

    @Basic
    @Column(name = "NO_PROPRIETARIO_BANCO",nullable = false)
    @ApiModelProperty(value = "noProprietarioBanco", required = true)
    public String getNoProprietarioBanco() {
        return noProprietarioBanco;
    }

    public void setNoProprietarioBanco(String noProprietarioBanco) {
        this.noProprietarioBanco = noProprietarioBanco;
    }

      

		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuracao other = (Configuracao) obj;
		if (dsModulo == null) {
			if (other.dsModulo != null)
				return false;
		} else if (!dsModulo.equals(other.dsModulo))
			return false;
		if (idConfiguracao != other.idConfiguracao)
			return false;
		if (idProjeto != other.idProjeto)
			return false;
		if (noModulo == null) {
			if (other.noModulo != null)
				return false;
		} else if (!noModulo.equals(other.noModulo))
			return false;
		if (noObjetoBanco == null) {
			if (other.noObjetoBanco != null)
				return false;
		} else if (!noObjetoBanco.equals(other.noObjetoBanco))
			return false;
		if (noProprietarioBanco == null) {
			if (other.noProprietarioBanco != null)
				return false;
		} else if (!noProprietarioBanco.equals(other.noProprietarioBanco))
			return false;
		return true;
	}

	
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dsModulo == null) ? 0 : dsModulo.hashCode());
		result = prime * result + (int) (idConfiguracao ^ (idConfiguracao >>> 32));
		result = prime * result + (int) (idProjeto ^ (idProjeto >>> 32));
		result = prime * result + ((noModulo == null) ? 0 : noModulo.hashCode());
		result = prime * result + ((noObjetoBanco == null) ? 0 : noObjetoBanco.hashCode());
		result = prime * result + ((noProprietarioBanco == null) ? 0 : noProprietarioBanco.hashCode());
		return result;
	}



}
