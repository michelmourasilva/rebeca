package br.rebeca.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private long coProjeto;
    private String noModulo;
    private String dsModulo;
    private String noObjetoBanco;
    private String noProprietarioBanco;
    
    private List<Filtro> filtros;

    
    @Id
    @Column(name = "ID_CONFIGURACAO_SERVICO")
    @ApiModelProperty(value = "id", hidden  = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getIdConfiguracao() {
        return idConfiguracao;
    }

    public void setIdConfiguracao(long idConfiguracao) {
        this.idConfiguracao = idConfiguracao;
    }

    @Basic
    @Column(name = "ID_PROJETO", nullable = false)
    @ApiModelProperty(value = "coProjeto", required = true)
    public long getCoProjeto() {
        return coProjeto;
    }

    public void setCoProjeto(long coProjeto) {
        this.coProjeto = coProjeto;
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

    @OneToMany(mappedBy = "configuracao")
	public List<Filtro> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<Filtro> filtros) {
		this.filtros = filtros;
	}
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idConfiguracao ^ (idConfiguracao >>> 32));
		return result;
	}

		
	public Configuracao(long idConfiguracao, long coProjeto, String noModulo, String dsModulo, String noObjetoBanco,
			String noProprietarioBanco) {
		super();
		this.idConfiguracao = idConfiguracao;
		this.coProjeto = coProjeto;
		this.noModulo = noModulo;
		this.dsModulo = dsModulo;
		this.noObjetoBanco = noObjetoBanco;
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
		if (idConfiguracao != other.idConfiguracao)
			return false;
		return true;
	}



}
