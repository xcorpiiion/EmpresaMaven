package entities;

import enums.Cargo;

public class Funcionario extends Pessoa {
	private Cargo cargo;
	private Double salario;
	public Funcionario(String nome, String email, Double salario, Cargo cargo) {
		super(nome, email);
		this.salario = salario;
		this.cargo = cargo;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Double getSalario() {
		return salario;
	}
	
	public void aumentaSalario() {
		
	}
	
	public void descontoSalario() {
		
	}
	

}
