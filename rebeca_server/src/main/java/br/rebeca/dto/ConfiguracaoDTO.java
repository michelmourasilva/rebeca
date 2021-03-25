package br.rebeca.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.rebeca.model.Configuracao;

public class ConfiguracaoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long idConfiguracao;
	
    @NotEmpty(message="Preenchimento obrigatório.")
    @Length(max=17, message="Campo tipo número que deve ter até 14 dígitos.")
	private long idProjeto;
    
    @NotEmpty(message="Preenchimento obrigatório.")
    @Length(max=20, message="O tamanho deve ser até 20 caracteres")
	private String noModulo;
    
    @NotEmpty(message="Preenchimento obrigatório.")
    @Length(max=300, message="O tamanho deve ser até 300 caracteres")
	private String dsModulo;
    
    @NotEmpty(message="Preenchimento obrigatório.")
    @Length(max=30, message="O tamanho deve ser até 30 caracteres")
	private String noProprietarioBanco;
    
    @NotEmpty(message="Preenchimento obrigatório.")
    @Length(max=30, message="O tamanho deve ser até 30 caracteres")
	private String noObjetoBanco;
	
	@JsonIgnore
	public long getIdConfiguracao() {
		return idConfiguracao;
	}
	

	public void setIdConfiguracao(long idConfiguracao) {
		this.idConfiguracao = idConfiguracao;
	}
	public long getIdProjeto() {
		return idProjeto;
	}
	public void setIdProjeto(long idProjeto) {
		this.idProjeto = idProjeto;
	}
	public String getDsModulo() {
		return dsModulo;
	}
	public void setDsModulo(String dsModulo) {
		this.dsModulo = dsModulo;
	}
	public String getNoModulo() {
		return noModulo;
	}
	public void setNoModulo(String noModulo) {
		this.noModulo = noModulo;
	}
	public String getNoProprietarioBanco() {
		return noProprietarioBanco;
	}
	public void setNoProprietarioBanco(String noProprietarioBanco) {
		this.noProprietarioBanco = noProprietarioBanco;
	}
	public String getNoObjetoBanco() {
		return noObjetoBanco;
	}
	public void setNoObjetoBanco(String noObjetoBanco) {
		this.noObjetoBanco = noObjetoBanco;
	}
	
    public ConfiguracaoDTO() {
    	super();
    }
	
	public ConfiguracaoDTO(Configuracao obj) {
		super();
		idConfiguracao = obj.getIdConfiguracao();
		noModulo = obj.getDsModulo();
		dsModulo = obj.getDsModulo();
		noObjetoBanco = obj.getNoObjetoBanco();
		noProprietarioBanco = obj.getNoProprietarioBanco();
	}
	
}
