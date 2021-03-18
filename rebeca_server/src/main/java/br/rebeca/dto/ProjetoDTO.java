package br.rebeca.dto;

import java.io.Serializable;

import br.rebeca.model.Projeto;


public class ProjetoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

	private long idProjeto;

	private String noProjeto;
    
    private String dsProjeto;
    
    public ProjetoDTO() {
    	super();
    }

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

	
	public ProjetoDTO(Projeto obj) {
		idProjeto = obj.getIdProjeto();
		noProjeto = obj.getDsProjeto();
		dsProjeto = obj.getNoProjeto();
	}
	
}
