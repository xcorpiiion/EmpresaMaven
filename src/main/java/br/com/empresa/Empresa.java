package br.com.empresa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Empresa {
	private String nome;
	private String email;
	private String cnpj;
	private Endereco endereco;
	private List<Produtos> produto = new ArrayList<Produtos>();
	private List<Funcionario> funcionario = new ArrayList<Funcionario>();
	private List<Cliente> cliente = new ArrayList<Cliente>();

	public Empresa(String nome, String email, List<Produtos> produto, String cnpj, Endereco endereco) throws Exception {
		this.nome = nome;
		this.email = email;
		this.produto = produto;
		this.cnpj = cnpj;
		this.endereco = endereco;
		verificaDados(nome, email, produto, cnpj, endereco);
	}
	
	public Endereco getEndereco() {
		return endereco;
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
		return "------Dados da empresa------" + "\nNome: " + this.getNome() + "\nEmail:  "
				+ this.getEmail() + "\nCnpj: " + this.getCnpj() 
				+ "\n------Dados dos funcionarios da empresa------"
				+ "\n" + this.getFuncionario().stream().map(func -> "\nNome: " + func.getNome() + " |Email: " + func.getEmail()
				+ " |Cargo: " + func.getCargo() + "\n").collect(Collectors.toList()).toString().replaceFirst(",", "")
				+ "\n------Dados dos produtos da empresa------"
				+ "\n" + this.getProduto().stream().map(p1 -> "\nNome: " + p1.getNome() + " |Quantidade em estoque: " + p1.getEstoque() + "\n").collect(Collectors.toList()).toString().replaceFirst(",", "")
				+ "\n------Endereço da empresa------"
				+ "\nRua: " + this.endereco.getRua() + "\nNúmero residência: " + this.endereco.getNumeroResidencia()
				+ "\nBairro: " + this.endereco.getBairro() + "\nCep: " + this.endereco.getBairro()
				+ "\nCidade: " + this.endereco.getCidade() + "\nEstado: " + this.endereco.getEstado();
	}

	public void verificaDados(String nome, String email, List<Produtos> produto, 
			String cnpj, Endereco endereco) throws Exception {
		if(cnpj == null || cnpj.isEmpty()) {
			throw new Exception("O cnpj não pode ficar null");
		}
		
		if (cnpj.length() != 14) {
			throw new Exception("O cnpj está errado");
		}
		if (cnpj.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
			throw new Exception("O cnpj está errado");
		} else if (cnpj.matches("^[0-9]*$")) {
			System.out.println("");
		} else {
			throw new Exception("O cnpj está errado");
		}
		
		if(nome == null || nome.isEmpty()) {
			throw new Exception("O nome está vazio ou null");
		}
		
		if(email == null || email.isEmpty()) {
			throw new Exception("O email está vazio ou null");
		}
		
		if(produto == null) {
			throw new Exception("O produto está null");
		}
		
		if(endereco== null) {
			throw new Exception("O endereço está null");
		}
	}
	
}
