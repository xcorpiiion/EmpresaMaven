package br.com.contmatic.enums;

public enum Cargo {
	REPOSITOR("REPOSITOR"),
	RH("RH");
	
	public String cargo;

	private Cargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
	}
	
}
