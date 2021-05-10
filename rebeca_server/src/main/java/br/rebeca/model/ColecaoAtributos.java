package br.rebeca.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import br.rebeca.model.ColecaoAtributos.ColecaoId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@Entity
@IdClass(ColecaoId.class)
@Table(name = "VW_OBJETOS_DISPONIVEIS", schema = "REBECA")
public class ColecaoAtributos implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Identificador único da coluna.")
	private Long idColuna;
	@ApiModelProperty(notes = "Nome do objeto que pode ser disponibilizado para visualização.")
	public String noObjeto;
	@ApiModelProperty(notes = "Nome da coluna")
	private String noColuna;
	@ApiModelProperty(notes = "Tipo de dado da coluna.")
	private String tpColuna;
	@ApiModelProperty(notes = "Tamanho da coluna")
	private String nuTamanhoColuna;
	@ApiModelProperty(notes = "Precisao númerica da coluna")
	private String nuPrecisaoColuna;
	@ApiModelProperty(notes = "Identifica se a coluna pode ou não ser nula.")
	private String tpNulo;

	public ColecaoAtributos() {
		super();
	}

	@Id
	@Basic
	@Column(name = "ID_COLUNA", insertable = false, updatable = false)
	@ApiModelProperty(value = "id", hidden = true)
	public long getIdColuna() {
		return idColuna;
	}

	public void setIdColuna(long idColuna) {
		this.idColuna = idColuna;
	}

	@Id
	@Basic
	@Column(name = "NO_OBJETO", insertable = false, updatable = false)
	@ApiModelProperty(value = "noObjeto", hidden = false)
	public String getNoObjeto() {
		return noObjeto;
	}

	public void setNoObjeto(String noObjeto) {
		this.noObjeto = noObjeto;
	}

	@Basic
	@Column(name = "NO_COLUNA")
	@ApiModelProperty(value = "noColuna", hidden = false)
	public String getNoColuna() {
		return noColuna;
	}

	public void setNoColuna(String noColuna) {
		this.noColuna = noColuna;
	}

	@Basic
	@Column(name = "TP_COLUNA")
	@ApiModelProperty(value = "tpColuna", hidden = false)
	public String getTpColuna() {
		return tpColuna;
	}

	public void setTpColuna(String tpColuna) {
		this.tpColuna = tpColuna;
	}

	@Basic
	@Column(name = "NU_TAMANHO_COLUNA")
	@ApiModelProperty(value = "nuTamanhoColuna", hidden = false)
	public String getNuTamanhoColuna() {
		return nuTamanhoColuna;
	}

	public void setNuTamanhoColuna(String nuTamanhoColuna) {
		this.nuTamanhoColuna = nuTamanhoColuna;
	}

	@Basic
	@Column(name = "NU_PRECISAO_COLUNA")
	@ApiModelProperty(value = "nuPrecisaoColuna", hidden = false)
	public String getNuPrecisaoColuna() {
		return nuPrecisaoColuna;
	}

	public void setNuPrecisaoColuna(String nuPrecisaoColuna) {
		this.nuPrecisaoColuna = nuPrecisaoColuna;
	}

	@Basic
	@Column(name = "TP_NULO")
	@ApiModelProperty(value = "tpNulo", hidden = false)
	public String getTpNulo() {
		return tpNulo;
	}

	public void setTpNulo(String tpNulo) {
		this.tpNulo = tpNulo;
	}

	public ColecaoAtributos(String noObjeto, String noColuna, String tpColuna, String nuTamanhoColuna, String nuPrecisaoColuna,
			String tpNulo) {
		super();
		this.noObjeto = noObjeto;
		this.noColuna = noColuna;
		this.tpColuna = tpColuna;
		this.nuTamanhoColuna = nuTamanhoColuna;
		this.nuPrecisaoColuna = nuPrecisaoColuna;
		this.tpNulo = tpNulo;
	}

	public static class ColecaoId implements Serializable {

		private static final long serialVersionUID = 1L;

		private Long idColuna;

		private String noObjeto;

		public ColecaoId() {
		}

		public ColecaoId(Long idColuna, String noObjeto) {
			super();
			this.setIdColuna(idColuna);
			this.setNoObjeto(noObjeto);
		}

		public Long getIdColuna() {
			return idColuna;
		}

		public void setIdColuna(Long idColuna) {
			this.idColuna = idColuna;
		}

		public String getNoObjeto() {
			return noObjeto;
		}

		public void setNoObjeto(String noObjeto) {
			this.noObjeto = noObjeto;
		}

	}

}
