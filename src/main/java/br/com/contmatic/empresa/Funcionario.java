package br.com.contmatic.empresa;

import java.text.SimpleDateFormat;

import enums.Cargo;
import enums.MotivoDemissao;
import enums.TipoContrato;

public class Funcionario extends Pessoa {
	
	private Cargo cargo;

	private Double salario;

	private Endereco endereco;

	private MotivoDemissao motivoDemissao;

	private TipoContrato tipoContrato;

	public Funcionario(String nome, String email, Double salario, Cargo cargo, SimpleDateFormat dataNascimento,
			TipoContrato tipoContrato, Endereco endereco) throws Exception {
		super(nome, email, dataNascimento);
		this.salario = salario;
		this.cargo = cargo;
		this.tipoContrato = tipoContrato;
		this.endereco = endereco;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

	public MotivoDemissao getMotivoDemissao() {
		return motivoDemissao;
	}

	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public void listaTarefasFuncionario(Funcionario funcionario) {
		if (funcionario.getCargo() == Cargo.RH) {
			System.out.println("Informe o que você quer fazer");
			System.out.println("1 - contratar um novo funcionario");
			System.out.println("2 - demitir um funcionario");
			System.out.println("3 - ver dados da empresa");
			System.out.println("4 - ver os seus dados");
			System.out.print("Informe uma opção: ");
		} else {
			System.out.println("Informe o que você quer fazer");
			System.out.println("1 - alterar dados de um produto");
			System.out.println("2 - cadastrar um novo produto");
			System.out.println("3 - ver os seus dados");
			System.out.print("Informe uma opção: ");
		}
	}

	// Apenas o Rh consegue contratar
	public void contratarFuncionario(String nome, String email, Cargo cargo, Double salario,
			SimpleDateFormat dataNascimento, Funcionario funcionario, Empresa loja, TipoContrato tipoContrato,
			Endereco endereco) throws Exception {
		if (funcionario == null) {
			throw new Exception("O funcionario está nullo");
		}

		if (funcionario.getCargo() != Cargo.RH) {
			throw new Exception("Apenas o RH pode contratar funcionarios");
		}
		if (loja.getFuncionario().stream()
				.anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email))) {
			throw new Exception("O funcionario já foi contratado");
		}
		loja.getFuncionario().add(new Funcionario(nome, email, salario, cargo, dataNascimento, tipoContrato, endereco));
	}
	
	public void demitirFuncionario(String nome, String email, MotivoDemissao motivoDemissao, Funcionario funcionario,
			Empresa loja) throws Exception {
		if (funcionario == null) {
			throw new Exception("O funcionario está nullo");
		}
		if (funcionario.getCargo() != Cargo.RH) {
			throw new Exception("Apenas o RH pode contratar funcionarios");
		}
		if (!loja.getFuncionario().stream()
				.anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email))) {
			throw new Exception("Não existe o funcionario com os dados informados");
		}
		switch (motivoDemissao) {
		case Justa_Causa:
			loja.getFuncionario().removeIf(
					(func1 -> func1.getEmail().equalsIgnoreCase(email) && func1.getNome().equalsIgnoreCase(nome)));
			break;
		case Pediu_As_Contas:
			loja.getFuncionario().removeIf(
					(func1 -> func1.getEmail().equalsIgnoreCase(email) && func1.getNome().equalsIgnoreCase(nome)));
			break;
		}
	}

	// Alterar dados do produto
	public void alterarDadosProduto(Funcionario funcionario, int escolhaOpcoes, String nomeProduto,
			String nomeNovoProduto, Empresa loja) throws Exception {
		if (funcionario == null) {
			throw new Exception("funcionario está null");
		}
		if (funcionario.getCargo() != Cargo.Repositor) {
			throw new Exception("Apenas repositores podem alterar os dados do produto");
		}
		if (escolhaOpcoes == 1) {
			for (Produtos prod : loja.getProduto()) {
				if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
					prod.setNome(nomeNovoProduto);
				}
			}
		}
	}

	public void alterarDadosProduto(Funcionario funcionario, int escolhaOpcoes, String nomeProduto, Double preco,
			Empresa loja) throws Exception {
		if (funcionario == null) {
			throw new Exception("funcionario está null");
		}
		if (funcionario.getCargo() != Cargo.Repositor) {
			throw new Exception("Apenas repositores podem alterar os dados do produto");
		}
		if (escolhaOpcoes == 2) {
			for (Produtos prod : loja.getProduto()) {
				if (prod.getNome().equals(nomeProduto)) {
					prod.setPreco(preco);
				}
			}
		}
	}

	public void alterarDadosProduto(Funcionario funcionario, int escolhaOpcoes, String nomeProduto, int estoque,
			Empresa loja) throws Exception {
		if (funcionario == null) {
			throw new Exception("funcionario está null");
		}
		if (funcionario.getCargo() != Cargo.Repositor) {
			throw new Exception("Apenas repositores podem alterar os dados do produto");
		}
		if (escolhaOpcoes == 3) {
			for (Produtos prod : loja.getProduto()) {
				if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
					prod.setEstoque(estoque);
				}
			}
		}
	}

	public void alterarDadosProduto(Funcionario funcionario, int escolhaOpcoes, String nomeProduto, double preco,
			int estoque, String nomeNovoProduto, Empresa loja) throws Exception {
		if (funcionario == null) {
			throw new Exception("funcionario está null");
		}
		if (funcionario.getCargo() != Cargo.Repositor) {
			throw new Exception("Apenas repositores podem alterar os dados do produto");
		}
		if (escolhaOpcoes == 4) {
			for (Produtos prod : loja.getProduto()) {
				if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
					prod.setNome(nomeNovoProduto);
					prod.setPreco(preco);
					prod.setEstoque(estoque);
				}
			}
		}
	}

	public void cadastrarProduto(String nome, Double preco, int estoque, Funcionario funcionario, Empresa loja)
			throws Exception {
		if (loja == null) {
			throw new Exception("A loja está null");
		}
		if (loja.getProduto() == null) {
			throw new Exception("O produto está null");
		}
		if (funcionario.getCargo() != Cargo.Repositor) {
			throw new Exception("Apenas repositores podem cadastra os produtos");
		}
		if (loja.getProduto().stream()
				.anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome) && prod.getPreco().equals(preco))) {
			loja.getProduto().stream()
					.filter(prod -> prod.getNome().equalsIgnoreCase(nome) && prod.getPreco().equals(preco))
					.forEach(prod -> prod.setEstoque(prod.getEstoque() + estoque));
		} else {
			loja.getProduto().add(new Produtos(nome, preco, estoque));
		}
	}

	public String toString(Funcionario funcionario) {
		return "------Dados do funcionario------" + "\nNome: " + funcionario.getNome() + "\nEmail: "
				+ funcionario.getEmail() + "\nCargo: " + funcionario.getCargo() + "Salario: " + funcionario.getSalario()
				+ "\n------Endereço------" + "\nRua: " + funcionario.getEndereco().getRua() + "\nNúmero residência: "
				+ funcionario.getEndereco().getNumeroResidencia() + "\nBairro: " + funcionario.getEndereco().getBairro()
				+ "\nCep: " + funcionario.getEndereco().getCep() + "\nCidade: " + funcionario.getEndereco().getCidade()
				+ "\nEstado: " + funcionario.getEndereco().getEstado();
	}

}
