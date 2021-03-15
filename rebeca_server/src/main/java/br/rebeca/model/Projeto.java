package br.rebeca.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Entity
@Table(name = "TB_PROJETO", schema = "REBECA")
public class Projeto implements Serializable{

	private static final long serialVersionUID = 1L;
    private long idProjeto;
    private String noProjeto;
    private String dsProjeto;
 
    
    @Id
    @Basic
    @Column(name = "ID_PROJETO")
    @ApiModelProperty(value = "id", hidden  = true)
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Projeto projeto = (Projeto) o;
        return idProjeto  == projeto.idProjeto &&
                Objects.equals(noProjeto, projeto.noProjeto) &&
                Objects.equals(dsProjeto, projeto.dsProjeto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProjeto , noProjeto, dsProjeto);
    }


}
