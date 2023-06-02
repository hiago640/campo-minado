package br.com.hiago640.cm.swing.modelo;

public enum PartidaEventoEnum {

	VITORIA(true), 
	DERROTA(false);
	
	private boolean value;

	private PartidaEventoEnum(boolean value) {
		this.value = value;
	}

	public boolean getValue() {
		return value;
	}

}
