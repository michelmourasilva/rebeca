package br.rebeca.model.enums;

public enum TipoFiltro {
	
	IGUAL(1, "= :1"),
	DIFERENTE(2, "!= :1"),
	MAIOR(3, "> :1"),
	MENOR(4, "< :1"),
	MAIOROUIGUAL(5, ">= :1"),
	MENOROUIGUAL(6, "<= :1"),
	IN(7, "( select * from (TABLE(REBECA.fnc_string_virgula_tabela(:1))))");
	
	private int codTipo;
	private String descTipo;
	
	private TipoFiltro(int codTipo, String descTipo) {
		this.codTipo = codTipo;
		this.descTipo = descTipo;
	}
	

	public int getCodTipo() {
		return codTipo;
	}

	public String getDescTipo() {
		return descTipo;
	}
	
	public static TipoFiltro toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (TipoFiltro x : TipoFiltro.values()) {
			if (cod.equals(x.getCodTipo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
