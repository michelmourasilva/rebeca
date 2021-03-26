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

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.rebeca.model.enums.TipoFiltro;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "TB_FILTRO_SERVICO", schema = "REBECA")
public class Filtro implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(notes = "Identificador gerado automaticamente pelo Oracle. Identificador que auxilia na identificação de um filtro que poderá ser utilizado dentro de uma configuração do serviço REST.")
    private long idFiltro;
	@ApiModelProperty(notes = "Nome do atributo que será utilizado como filtro")
	private String noAtributo;
    
    private Configuracao configuracaoservico;
    
    @ApiModelProperty(notes = "Tipo de operacao que podera ser aplicada em um campo especifico do objeto. Valores possiveis: IGUAL(0, “= :1”), DIFERENTE(1, “!= :1”), MAIOR(2, “> :1”),MENOR(3, “< :1”), MAIOROUIGUAL(4, “>= :1”), MENOROUIGUAL(5, “<= :1”), IN(6, “( select * from (TABLE(REBECA.fnc_string_virgula_tabela(:1))))”); Obs: Para operaćões utilizando IN, será utilizado uma funćão que irá quebrar as strings passadas no final do endpoint e transforma-las em uma colećão interável do Oracle. Ex; http://localhost:8080/data-set/all/PROJETO/MODULO/6/35228266,35274100,35442987 <<- Aonde o número 6 é o filtro criado para esse atributo.")
    private Integer tipofiltro;


    @Id
    @Column(name = "ID_FILTRO_SERVICO")
    @ApiModelProperty(value = "id", hidden  = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getIdFiltro() {
        return idFiltro;
    }

   
	public void setIdFiltro(long idFiltro) {
        this.idFiltro = idFiltro;
    }

    @Basic
    @Column(name = "NO_ATRIBUTO", nullable = false)
    @ApiModelProperty(value = "noAtributo", required = true)
    public String getNoAtributo() {
        return noAtributo;
    }

    public void setNoAtributo(String noAtributo) {
        this.noAtributo = noAtributo;
    }
    
    @JsonIgnore
    @ManyToOne(optional=false)
    @JoinColumn(name="ID_CONFIGURACAO_SERVICO", nullable=false)
    public Configuracao getconfiguracaoservico() {
        return configuracaoservico;
    }

    public void setconfiguracaoservico(Configuracao vconfiguracaoservico) {
    	configuracaoservico = vconfiguracaoservico;
    }

    
    @Basic
    @Column(name = "TP_CONDICAO", nullable = false)
    @ApiModelProperty(value = "tpCondicao", required = true)
    public TipoFiltro getTipoFiltro() {
    	return TipoFiltro.toEnum(tipofiltro);
    }
    
    public void setTipoFiltro(TipoFiltro tipofiltro) {
    	this.tipofiltro = tipofiltro.getCodTipo();
    }
    
    
    public Filtro() {
        super();
    }

        
	public Filtro(String noAtributo, Configuracao configuracaoservico, TipoFiltro tipofiltro) {
	//public Filtro(String noAtributo, Configuracao configuracaoservico) {
		super();
		this.noAtributo = noAtributo;
		this.configuracaoservico = configuracaoservico;
		this.tipofiltro = (tipofiltro==null) ? null : tipofiltro.getCodTipo();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idFiltro ^ (idFiltro >>> 32));
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
		Filtro other = (Filtro) obj;
		if (idFiltro != other.idFiltro)
			return false;
		return true;
	}



    
    
}
