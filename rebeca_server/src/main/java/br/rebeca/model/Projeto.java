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
    
    @ApiModelProperty(notes = "Identificador gerado automaticamente pelo Oracle. Auxilia na identificação da configuração do serviço REST.")
	private long idProjeto;
    @ApiModelProperty(notes = "Nome único do projeto que irá disponibilizar dados para o Rebeca")
    private String noProjeto;
    @ApiModelProperty(notes = "Breve descrição do projeto.")
    private String dsProjeto;
    @ApiModelProperty(notes = "URL que poderá ser usada para encaminhar para links externos referente ao projeto.")
    private String txtURL;
    @ApiModelProperty(notes = "Imagem que será apresentada no card principal do projeto.")
    private String txtCaminhoImagem;
    
    private Set<Configuracao> configuracoes;
    
    
    public Projeto() {
    	super();
    }
    

	public Projeto(long idProjeto, String noProjeto, String dsProjeto, String txtURL, String txtCaminhoImagem) {
		super();
		this.idProjeto = idProjeto;
		this.noProjeto = noProjeto;
		this.dsProjeto = dsProjeto;
		this.txtURL = txtURL;
		this.txtCaminhoImagem = txtCaminhoImagem;
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

	
    @Basic
    @ApiModelProperty(value = "txtURL", required = true)
    @Column(name = "TX_URL", nullable = true)
	public String getTxtURL() {
		return txtURL;
	}

	public void setTxtURL(String txtURL) {
		this.txtURL = txtURL;
	}
	
    @Basic
    @ApiModelProperty(value = "txtCaminhoImagem", required = true)
    @Column(name = "TX_CAMINHO_IMAGEM", nullable = true)
	public String getTxtCaminhoImagem() {
		return txtCaminhoImagem;
	}

	public void setTxtCaminhoImagem(String txtCaminhoImagem) {
		this.txtCaminhoImagem = txtCaminhoImagem;
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
