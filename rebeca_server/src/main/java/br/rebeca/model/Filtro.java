package br.rebeca.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "TB_FILTRO_SERVICO", schema = "REBECA")
public class Filtro implements Serializable{

	private static final long serialVersionUID = 1L;
    private long idFiltro;
    private String cdCondicao;
    

    private Configuracao configuracao;

    @Id
    @Column(name = "ID_FILTRO_SERVICO")
    @ApiModelProperty(value = "id", hidden  = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getIdFiltro() {
        return idFiltro;
    }

    public Filtro(long idFiltro, String cdCondicao, Configuracao configuracao) {
		super();
		this.idFiltro = idFiltro;
		this.cdCondicao = cdCondicao;
		this.configuracao = configuracao;
	}

	public void setIdFiltro(long idFiltro) {
        this.idFiltro = idFiltro;
    }

    @Basic
    @Column(name = "CD_CONDICAO")
    public String getcdCondicao() {
        return cdCondicao;
    }

    public void setcdCondicao(String cdCondicao) {
        this.cdCondicao = cdCondicao;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idFiltro ^ (idFiltro >>> 32));
		return result;
	}

	@ManyToOne(targetEntity = Configuracao.class)
	@JoinColumn(name="ID_CONFIGURACAO_SERVICO")
	public Configuracao getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filtro other = (Filtro) obj;
		if (idFiltro != other.idFiltro)
			return false;
		return true;
	}



    
    
}
