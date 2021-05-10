package br.rebeca.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "TB_CONFIGURACAO_SERVICO", schema = "REBECA", catalog = "",uniqueConstraints={
	    @UniqueConstraint(columnNames = {"ID_PROJETO", "NO_MODULO","NO_OBJETO_BANCO","NO_PROPRIETARIO_BANCO"} )})
public class Configuracao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** Primary key. */
    protected static final String PK = "idConfiguracao";
	
    @ApiModelProperty(notes = "Identificador gerado automaticamente pelo Oracle. Auxilia na identificação da configuração do serviço REST.")
	private long idConfiguracao;
    @ApiModelProperty(notes = "Nome do módulo que será passada pela URI do serviço REST (URI - Identificador de Recursos Universal, como diz o próprio nome, é o identificador do recurso. Pode ser uma imagem, uma página, etc, pois tudo o que está disponível na internet precisa de um identificador único para que não seja confundido.")
    private String noModulo;
    @ApiModelProperty(notes = "Breve descrição do módulo que está sendo acessado.")
    private String dsModulo;
    @ApiModelProperty(notes = "Nome físico do objeto dentro do banco de dados.")
    private String noObjetoBanco;
    @ApiModelProperty(notes = "Nome do owner do objeto dentro do banco de dados.")
    private String noProprietarioBanco;
    
    private Projeto projeto;
    private Set<Filtro> filtros;
    

	public Configuracao() {
    	super();
    }
    
	public Configuracao(String noModulo, String dsModulo, String noObjetoBanco, String noProprietarioBanco,
			Projeto projeto) {
		super();
		this.noModulo = noModulo;
		this.dsModulo = dsModulo;
		this.noObjetoBanco = noObjetoBanco;
		this.noProprietarioBanco = noProprietarioBanco;
		this.projeto = projeto;

	}

	@Id
    @Column(name = "ID_CONFIGURACAO_SERVICO", unique=true, nullable=false, precision=14)
    @ApiModelProperty(value = "id", hidden=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getIdConfiguracao() {
		return idConfiguracao;
	}

	public void setIdConfiguracao(long idConfiguracao) {
		this.idConfiguracao = idConfiguracao;
	}

    @Basic
    @Column(name = "NO_MODULO", nullable = false,  length=30)
    @ApiModelProperty(value = "noModulo", required = true)
	public String getNoModulo() {
		return noModulo;
	}

	public void setNoModulo(String noModulo) {
		this.noModulo = noModulo;
	}

    @Basic
    @Column(name = "DS_MODULO", nullable = false,  length=30)
    @ApiModelProperty(value = "dsModulo", required = true)
	public String getDsModulo() {
		return dsModulo;
	}

	public void setDsModulo(String dsModulo) {
		this.dsModulo = dsModulo;
	}

    @Basic
    @Column(name = "NO_OBJETO_BANCO",nullable = false,  length=30)
    @ApiModelProperty(value = "noObjetoBanco", required = true)
	public String getNoObjetoBanco() {
		return noObjetoBanco;
	}


	public void setNoObjetoBanco(String noObjetoBanco) {
		this.noObjetoBanco = noObjetoBanco;
	}

    @Basic
    @Column(name = "NO_PROPRIETARIO_BANCO",nullable = false,  length=30)
    @ApiModelProperty(value = "noProprietarioBanco", required = true)
	public String getNoProprietarioBanco() {
		return noProprietarioBanco;
	}

	public void setNoProprietarioBanco(String noProprietarioBanco) {
		this.noProprietarioBanco = noProprietarioBanco;
	}

	@JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name="ID_PROJETO", nullable=false)
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto vprojeto) {
		projeto = vprojeto;
	}

    @OneToMany(mappedBy="configuracaoservico")
    public Set<Filtro> getFiltros() {
        return filtros;
    }

    public void setFiltros(Set<Filtro> vfiltros) {
        filtros = vfiltros;
    }
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idConfiguracao ^ (idConfiguracao >>> 32));
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
		Configuracao other = (Configuracao) obj;
		if (idConfiguracao != other.idConfiguracao)
			return false;
		return true;
	}

	


}
