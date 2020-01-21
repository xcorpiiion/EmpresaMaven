package br.com.contmatic.empresa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Empresa {

	private String nome;

	private String email;

	private String cnpj;

	private Endereco endereco;

	private List<Produtos> produto = new ArrayList<>();

	private List<Funcionario> funcionario = new ArrayList<>();

	private List<Cliente> cliente = new ArrayList<>();

	public Empresa(String nome, String email, List<Produtos> produto, String cnpj, Endereco endereco) throws Exception {
		this.nome = nome;
		this.email = email;
		this.produto = produto;
		this.cnpj = cnpj;
		this.endereco = endereco;
		nome_nao_deve_ser_null_ou_vazio(nome);
		email_nao_deve_ser_null_ou_vazio(email);
		nao_deve_aceitar_produto_null(produto);
		nao_deve_aceitar_cnpj_com_menos_de_14_digitos(cnpj);
		nao_deve_aceitar_cnpj_null(cnpj);
		nao_deve_aceitar_cnpj_vazio(cnpj);
		nao_deve_aceitar_letras_no_cnpj(cnpj);
		nao_deve_aceitar_endereco_null(endereco);
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
	public boolean produto_deve_existir(String nome) {
		return this.produto.stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome));
	}

	// Verifica se eu tenho cadastro na loja ou se eu sou um funcionario da loja
	public boolean verifica_login(String nome, String email, int verificaFuncOuCliente) {
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
	public Cliente return_cliente_fez_login(String nome, String email) throws Exception {
		for (Cliente clien : this.cliente) {
			if (clien.getNome().equalsIgnoreCase(nome) && clien.getEmail().equalsIgnoreCase(email)) {
				return clien;
			}
		}
		throw new Exception("O cliente que fez o login não existe");
	}

	// retorna o funcionario que fez o login
	public Funcionario return_funcionario_fez_login(String nome, String email) throws Exception {
		for (Funcionario func : this.funcionario) {
			if (func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email)) {
				return func;
			}
		}
		throw new Exception("O cliente que fez o login não existe");
	}

	public void mostrarProdutos() {
		this.produto.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
	}

	private void nome_nao_deve_ser_null_ou_vazio(String nome) throws Exception {
		if(nome == null || nome.isEmpty() || nome.trim().equals("")) {
			throw new Exception("O nome está null ou vazio");
		}
	}
	
	private void email_nao_deve_ser_null_ou_vazio(String email) throws Exception {
		if(email == null || email.isEmpty() || email.trim().equals("")) {
			throw new Exception("O email está null ou vazio");
		}
	}
	
	private void nao_deve_aceitar_cnpj_null(String cnp) throws Exception {
		if (cnpj == null) {
			throw new Exception("O cnpj não pode ficar null");
		}
	}
	
	private void nao_deve_aceitar_cnpj_vazio(String cnp) throws Exception {
		if (cnpj.isEmpty() || cnpj.trim().equals("")) {
			throw new Exception("O cnpj não pode ficar vazio");
		}
	}
	
	private void nao_deve_aceitar_letras_no_cnpj(String cnp) throws Exception {
		if (cnpj.length() != 14) {
			throw new Exception("O cnpj está errado");
		}
		if (cnpj.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
			throw new Exception("O cnpj está errado");
		} else if (cnpj.matches("^[0-9]*$")) {
		} else {
			throw new Exception("O cnpj está errado");
		}
	}
	
	private void nao_deve_aceitar_cnpj_com_menos_de_14_digitos(String cnpj) throws Exception {
		if (cnpj.length() != 14) {
			throw new Exception("O cnpj está errado");
		}
	}
	
	private void nao_deve_aceitar_produto_null(List<Produtos> produto) throws Exception {
		if (produto == null) {
			throw new Exception("O produto está null");
		}
	}
	
	private void nao_deve_aceitar_endereco_null(Endereco endereco) throws Exception {
		if (endereco == null) {
			throw new Exception("O endereço está null");
		}
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
		return "------Dados da empresa------" + "\nNome: " + this.getNome() + "\nEmail:  " + this.getEmail()
				+ "\nCnpj: " + this.getCnpj() + "\n------Dados dos funcionarios da empresa------" + "\n"
				+ this.getFuncionario().stream()
						.map(func -> "\nNome: " + func.getNome() + " |Email: " + func.getEmail() + " |Cargo: "
								+ func.getCargo() + "\n")
						.collect(Collectors.toList()).toString().replaceFirst(",", "")
				+ "\n------Clientes da empresa------" + "\n"
				+ this.getCliente().stream()
						.map(clien -> "\nNome: " + clien.getNome() + " |Email: " + clien.getEmail() + "\n")
						.collect(Collectors.toList()).toString().replaceFirst(",", "")
				+ "\n------Dados dos produtos da empresa------" + "\n"
				+ this.getProduto().stream()
						.map(p1 -> "\nNome: " + p1.getNome() + " |Quantidade em estoque: " + p1.getEstoque() + "\n")
						.collect(Collectors.toList()).toString().replaceFirst(",", "")
				+ "\n------Endereço da empresa------" + "\nRua: " + this.endereco.getRua() + "\nNúmero residência: "
				+ this.endereco.getNumeroResidencia() + "\nBairro: " + this.endereco.getBairro() + "\nCep: "
				+ this.endereco.getBairro() + "\nCidade: " + this.endereco.getCidade() + "\nEstado: "
				+ this.endereco.getEstado();
	}

}
