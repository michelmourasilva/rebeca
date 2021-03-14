package br.rebeca.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Entity
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
    private Collection<Filtro> Filtros;

    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuracao that = (Configuracao) o;
        return idConfiguracao == that.idConfiguracao &&
                coProjeto == that.coProjeto &&
                Objects.equals(noModulo, that.noModulo) &&
                Objects.equals(dsModulo, that.dsModulo) &&
                Objects.equals(noObjetoBanco, that.noObjetoBanco) &&
                Objects.equals(noProprietarioBanco, that.noProprietarioBanco);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idConfiguracao, coProjeto, noModulo, dsModulo, noObjetoBanco, noProprietarioBanco);
    }

    @OneToMany(mappedBy = "tbConfiguracaoByIdConfiguracao")
    @JsonManagedReference
    public Collection<Filtro> getFiltros() {
        return Filtros;
    }

    public void setFiltros(Collection<Filtro> Filtros) {
        this.Filtros = Filtros;
    }
}
