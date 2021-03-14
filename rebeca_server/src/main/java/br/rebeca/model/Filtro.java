package br.rebeca.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "TB_FILTRO_SERVICO", schema = "REBECA")
public class Filtro implements Serializable{

	private static final long serialVersionUID = 1L;
    private long idFiltro;
    private String cdCondicao;
    private Configuracao tbConfiguracaoByIdConfiguracao;

    @Id
    @Column(name = "ID_FILTRO_SERVICO")
    public long getIdFiltro() {
        return idFiltro;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filtro filtro = (Filtro) o;
        return idFiltro == filtro.idFiltro &&
                Objects.equals(cdCondicao, filtro.cdCondicao) ;
    }


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ID_CONFIGURACAO_SERVICO", referencedColumnName = "ID_CONFIGURACAO_SERVICO", nullable = false)
    public Configuracao getTbConfiguracaoByIdConfiguracao() {
        return tbConfiguracaoByIdConfiguracao;
    }

    public void setTbConfiguracaoByIdConfiguracao(Configuracao tbConfiguracaoByIdConfiguracao) {
        this.tbConfiguracaoByIdConfiguracao = tbConfiguracaoByIdConfiguracao;
    }
}
