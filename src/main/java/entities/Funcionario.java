package entities;

import java.text.SimpleDateFormat;

import enums.Cargo;

public class Funcionario extends Pessoa {
	private Cargo cargo;
	private Double salario;

	public Funcionario(String nome, String email, Double salario, Cargo cargo, SimpleDateFormat dataNascimento) {
		super(nome, email, dataNascimento);
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

	public void aumentaSalario(Double salario) {
		this.salario += salario;
	}

	public void descontoSalario(Double descontoSalario) {
		this.salario += descontoSalario;
	}

	public void listaTarefasFuncionario(Funcionario funcionario) {
		if (funcionario.getCargo() == Cargo.RH) {
			System.out.println("Informe o que você quer fazer");
			System.out.println("1 - contratar um novo funcionario");
			System.out.println("2 - demitir um funcionario");
			System.out.println("3 - da um aumento à um funcionario");
			System.out.println("4 - descontar um valor de um funcionario");
			System.out.print("Informe uma opção: ");
		} else {
			System.out.println("Informe o que você quer fazer");
			System.out.println("1 - alterar dados de um produto");
			System.out.println("2 - cadastrar um novo produto");
			System.out.print("Informe uma opção: ");
		}
	}

}
