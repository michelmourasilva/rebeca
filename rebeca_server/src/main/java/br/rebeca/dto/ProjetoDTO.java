package br.rebeca.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.rebeca.model.Projeto;
import io.swagger.annotations.ApiModelProperty; 

public class ProjetoDTO  implements Serializable{

	private static final long serialVersionUID = 1L;


	private long idProjeto;

    @NotEmpty(message="Preenchimento obrigatório.")
    @Length(max=30, message="O tamanho deve ser até 30 caracteres")
    @ApiModelProperty(notes = "Nome único do projeto que irá disponibilizar dados para o Rebeca")
	private String noProjeto;
    

    @NotEmpty(message="Preenchimento obrigatório.")
    @Length(max=4000, message="O tamanho deve ser até 4000 caracteres")
    @ApiModelProperty(notes = "Breve descrição do projeto.")
    private String dsProjeto;
    

    @Length(max=4000, message="O tamanho deve ser até 4000 caracteres")
    @ApiModelProperty(notes = "URL que poderá ser usada para encaminhar para links externos referente ao projeto.")
    private String txtURL;
    
    @Length(max=4000, message="O tamanho deve ser até 4000 caracteres")
    @ApiModelProperty(notes = "Imagem que será apresentada no card principal do projeto.")
    private String txtCaminhoImagem;
    
    
    public ProjetoDTO() {
    	super();
    }

    @JsonIgnore
	public long getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(long idProjeto) {
		this.idProjeto = idProjeto;
	}

	public String getNoProjeto() {
		return noProjeto;
	}

	public void setNoProjeto(String noProjeto) {
		this.noProjeto = noProjeto;
	}

	public String getDsProjeto() {
		return dsProjeto;
	}

	public void setDsProjeto(String dsProjeto) {
		this.dsProjeto = dsProjeto;
	}


	public String getTxtURL() {
		return txtURL;
	}

	public void setTxtURL(String txtURL) {
		this.txtURL = txtURL;
	}

	public String getTxtCaminhoImagem() {
		return txtCaminhoImagem;
	}

	public void setTxtCaminhoImagem(String txtCaminhoImagem) {
		this.txtCaminhoImagem = txtCaminhoImagem;
	}

	public ProjetoDTO(Projeto obj) {
		idProjeto = obj.getIdProjeto();
		noProjeto = obj.getDsProjeto();
		dsProjeto = obj.getNoProjeto();
		txtURL = obj.getTxtURL();
		txtCaminhoImagem = obj.getTxtCaminhoImagem();
	}
	
}
