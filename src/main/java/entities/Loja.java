package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import enums.Cargo;
import enums.MotivoDemissao;
import enums.TipoContrato;

public class Loja {
	private String nome, email;
	private MotivoDemissao motivoDemissao;
	private TipoContrato tipoContrato;
	private List<Produtos> produto = new ArrayList<Produtos>();
	private List<Funcionario> funcionario = new ArrayList<Funcionario>();
	private List<Cliente> cliente = new ArrayList<Cliente>();

	public Loja(String nome, String email, List<Produtos> produto) {
		this.nome = nome;
		this.email = email;
		this.produto = produto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Produtos> getProduto() {
		return produto;
	}

	public List<Funcionario> getFuncionario() {
		return funcionario;
	}

	public List<Cliente> getCliente() {
		return cliente;
	}

	// Verifica se eu tenho cadastro na loja ou se eu sou um funcionario da loja
	public boolean verificarLogin(String nome, String email, int verificaFuncOuCliente) {
		// Verifica toda a lista e vai retorna true se existir um nome e email na lista
		if (verificaFuncOuCliente == 2) {
			if (this.funcionario.stream().anyMatch(
					func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email))) {
				return true;
			}
		} else {
			if (this.cliente.stream().anyMatch(
					clien -> clien.getNome().equalsIgnoreCase(nome) && clien.getEmail().equalsIgnoreCase(email))) {
				return true;
			}
		}

		return false;
	}

	// Apenas o Rh consegue contratar
	public void contratarFuncionario(String nome, String email, Cargo cargo, Double salario,
			SimpleDateFormat dataNascimento, Funcionario funcionario) throws Exception {
		if (funcionario == null) {
			throw new Exception("O funcionario está nullo");
		}

		if (funcionario.getCargo() != Cargo.RH) {
			throw new Exception("Apenas o RH pode contratar funcionarios");
		}

		// Verifica se o funcionario já existe
		if (this.funcionario.stream()
				.anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email))) {
			throw new Exception("O funcionario já foi contratado");
		}

		// Caso o tenha passado na verificação, o funcionario será contratado
		this.funcionario.add(new Funcionario(nome, email, salario, Cargo.Vendedor, dataNascimento));
		System.out.println("O funcionario foi contratado");
	}
	
	// Apenas o Rh consegue demitir um funcionario
	public void demitirFuncionario(String nome, String email, MotivoDemissao motivoDemissao, Funcionario funcionario)
			throws Exception {
		if (funcionario == null) {
			throw new Exception("O funcionario está nullo");
		}

		if (funcionario.getCargo() != Cargo.RH) {
			throw new Exception("Apenas o RH pode contratar funcionarios");
		}

		// Verifica se o funcionario existe
		if (this.funcionario.stream()
				.anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email))) {
			throw new Exception("Não existe o funcionario com os dados informados");
		}

		motivoDemissao = MotivoDemissao.Justa_Causa;
		switch (motivoDemissao) {
		case Justa_Causa:
			this.funcionario.removeIf(
					(func1 -> func1.getEmail().equalsIgnoreCase(email) && func1.getNome().equalsIgnoreCase(nome)));
			break;
		case Pediu_As_Contas:
			this.funcionario.removeIf(
					(func1 -> func1.getEmail().equalsIgnoreCase(email) && func1.getNome().equalsIgnoreCase(nome)));
			break;
		}

		// Caso o codigo passe no teste, ele vai demitir o funcionario
		System.out.println("O funcionario foi demitido");
	}

	// Qualuqer pessoa consegue se cadastrar na loja
	public void cadastrarCliente(String nome, String email, Double dinheiro, SimpleDateFormat dataNascimento)
			throws Exception {
		if (this.cliente == null) {
			throw new Exception("O cliente está nullo");
		}

		// Verifica se o cliente já existe
		if (this.cliente.stream()
				.anyMatch(c -> c.getNome().equalsIgnoreCase(nome) && c.getEmail().equalsIgnoreCase(email))) {
			throw new Exception("O cliente já está cadastrado");
		}

		// Caso o tenha passado na verificação, o funcionario será contratado
		cliente.add(new Cliente(nome, email, dinheiro, dataNascimento));
		System.out.println("O cliente foi cadastrado");
	}

	// Apenas o repositor consegue colocar novos produtos
	public void cadastrarProduto(String nome, Double preco, int estoque, Funcionario funcionario) throws Exception {
		if (this.produto == null) {
			throw new Exception("O produto está nullo");
		}

		// Verifica se o funcionario é um repositor
		if (funcionario.getCargo() == Cargo.Repositor) {
			throw new Exception("Apenas repositores podem cadastra os produtos");
		}

		// Verifica se o produto está no estoque
		boolean hasEstoque = this.produto.stream()
				.anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome) && prod.getPreco().equals(preco));

		if (hasEstoque) {
			this.produto.stream().filter(prod -> prod.getNome().equalsIgnoreCase(nome) && prod.getPreco().equals(preco))
					.forEach(prod -> prod.setEstoque(prod.getEstoque() + estoque));
			System.out.println("Produto foi add ao estoque");
		} else {
			this.produto.add(new Produtos(nome, preco, estoque));
			System.out.println("Produto foi cadastrado");
		}
	}
	
	public void mostrarProdutos() {
		System.out.println("Lista de produtos da loja");
		this.produto.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
		this.produto.forEach(System.out::println);
	}
}
