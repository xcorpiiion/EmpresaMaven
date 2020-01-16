package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import enums.Cargo;
import enums.MotivoDemissao;
import enums.TipoContrato;

public class Empresa {
	private String nome, email,cnpj;
	private String rua, bairro, cep;
	private MotivoDemissao motivoDemissao;
	private TipoContrato tipoContrato;
	private List<Produtos> produto = new ArrayList<Produtos>();
	private List<Funcionario> funcionario = new ArrayList<Funcionario>();
	private List<Cliente> cliente = new ArrayList<Cliente>();

	public Empresa(String nome, String email, List<Produtos> produto, String cnjp, String rua, String bairro
			, String cep) {
		this.nome = nome;
		this.email = email;
		this.produto = produto;
		this.cnpj = cnjp;
		this.rua = rua;
		this.bairro = bairro;
		this.cep = cep;
	}

	public String getNome() {
		return nome;
	}

	public String getCnpj() {
		return cnpj;
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

	// Alterar dados do produto
	public void alterarDadosProduto(Funcionario funcionario, int escolhaOpcoes, String nomeProduto) throws Exception {
		if (funcionario == null) {
			throw new Exception("funcionario está null");
		}

		if (funcionario.getCargo() != Cargo.Repositor) {
			throw new Exception("Apenas repositores podem alterar os dados do produto");
		}

		if (escolhaOpcoes == 1) {
			for (Produtos prod : this.produto) {
				if (prod.getNome().equals(nomeProduto)) {
					System.out.println("Entrou aqui");
					prod.setNome(nomeProduto);
				}
			}
		}
		System.out.println("Produto alterado com sucesso");
	}

	// Alterar dados do produto
	public void alterarDadosProduto(Funcionario funcionario, int escolhaOpcoes, String nomeProduto, Double preco)
			throws Exception {
		if (funcionario == null) {
			throw new Exception("funcionario está null");
		}

		if (funcionario.getCargo() != Cargo.Repositor) {
			throw new Exception("Apenas repositores podem alterar os dados do produto");
		}

		if (escolhaOpcoes == 2) {
			for (Produtos prod : this.produto) {
				if (prod.getNome().equals(nomeProduto)) {
					prod.setPreco(preco);
				}
			}
		}
		System.out.println("Produto alterado com sucesso");
	}

	// Alterar dados do produto
	public void alterarDadosProduto(Funcionario funcionario, int escolhaOpcoes, String nomeProduto, int estoque)
			throws Exception {
		if (funcionario == null) {
			throw new Exception("funcionario está null");
		}

		if (funcionario.getCargo() != Cargo.Repositor) {
			throw new Exception("Apenas repositores podem alterar os dados do produto");
		}

		if (escolhaOpcoes == 3) {
			for (Produtos prod : this.produto) {
				if (prod.getNome().equals(nomeProduto)) {
					prod.setEstoque(estoque);
				}
			}
		}
		System.out.println("Produto alterado com sucesso");
	}

	// Alterar dados do produto
	public void alterarDadosProduto(Funcionario funcionario, int escolhaOpcoes, String nomeProduto, double preco,
			int estoque) throws Exception {
		if (funcionario == null) {
			throw new Exception("funcionario está null");
		}

		if (funcionario.getCargo() != Cargo.Repositor) {
			throw new Exception("Apenas repositores podem alterar os dados do produto");
		}

		if (escolhaOpcoes == 4) {
			for (Produtos prod : this.produto) {
				if (prod.getNome().equals(nomeProduto)) {
					prod.setNome(nomeProduto);
					prod.setPreco(preco);
					prod.setEstoque(estoque);
				}
			}
		}
		System.out.println("Produto alterado com sucesso");
	}

	// verifica se o produto existe e retorna true ou false
	public boolean verificarProdutoExiste(String nome) {
		// Verifica toda a lista e vai retorna true se existir um nome e email na lista

		return this.produto.stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome));

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

	// retorna o cliente que fez o login
	public Cliente returnClienteQueFezLogin(String nome, String email) throws Exception {
		for (Cliente clien : this.cliente) {
			if (clien.getNome().equalsIgnoreCase(nome) && clien.getEmail().equalsIgnoreCase(email)) {
				return clien;
			}
		}
		throw new Exception("O cliente que fez o login não existe");
	}

	// retorna o funcionario que fez o login
	public Funcionario returnFuncionarioQueFezLogin(String nome, String email) throws Exception {
		for (Funcionario func : this.funcionario) {
			if (func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email)) {
				return func;
			}
		}
		throw new Exception("O cliente que fez o login não existe");
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
		this.funcionario.add(new Funcionario(nome, email, salario, cargo, dataNascimento));
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
		if (!this.funcionario.stream()
				.anyMatch(func -> func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email))) {
			throw new Exception("Não existe o funcionario com os dados informados");
		}

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
		if (funcionario.getCargo() != Cargo.Repositor) {
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

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Empresa [nome=" + nome + ", email=" + email + ", cnpj=" + cnpj + ", rua=" + rua + ", bairro=" + bairro
				+ ", cep=" + cep + ", produto=" + produto + ", funcionario=" + funcionario + ", cliente=" + cliente
				+ "]";
	}

	
	
	
}
