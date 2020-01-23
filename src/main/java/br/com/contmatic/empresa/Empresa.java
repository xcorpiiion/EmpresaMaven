package br.com.contmatic.empresa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.contmatic.constantes.ValidationNullOrEmpty;
import br.com.contmatic.services.EmptyStringException;

public class Empresa {
	
	private String nome;

	private String email;

	private String cnpj;

	private Endereco endereco;

	private List<Produto> produto;

	private List<Funcionario> funcionario = new ArrayList<>();

	private List<Cliente> cliente = new ArrayList<>();

	public Empresa(String nome, String email, List<Produto> produto, String cnpj, Endereco endereco) {
		setNome(nome);
		setEmail(email);
		this.produto = produto;
		setCnpj(cnpj);
		setEndereco(endereco);
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		ValidationNullOrEmpty.enderecoIsNull(endereco);
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		cnpjIsNull(cnpj);
		cnpjIsEmpty(cnpj);
		cnpjHasWord(cnpj);
		cnpjSizeValidation(cnpj);
		this.cnpj = cnpj;
	}

	public void setNome(String nome) {
		ValidationNullOrEmpty.nomeIsNull(nome);
		ValidationNullOrEmpty.nomeIsEmpty(nome);
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		ValidationNullOrEmpty.emailIsNull(email);
		ValidationNullOrEmpty.emailIsEmpty(email);
		this.email = email;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public List<Funcionario> getFuncionario() {
		return funcionario;
	}

	public List<Cliente> getCliente() {
		return cliente;
	}

	public boolean produtoExiste(String nome) {
		return this.produto.stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome));
	}

	public boolean verificaLogin(String nome, String email, int verificaFuncOuCliente) {
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

	public Cliente clienteThatDoLogin(String nome, String email) {
		for (Cliente clien : this.cliente) {
			if (clien.getNome().equalsIgnoreCase(nome) && clien.getEmail().equalsIgnoreCase(email)) {
				return clien;
			}
		}
		throw new IllegalArgumentException("O cliente que fez o login não existe");
	}

	public Funcionario funcionarioThatDoLogin(String nome, String email) {
		for (Funcionario func : this.funcionario) {
			if (func.getNome().equalsIgnoreCase(nome) && func.getEmail().equalsIgnoreCase(email)) {
				return func;
			}
		}
		throw new IllegalArgumentException("O cliente que fez o login não existe");
	}

	public void mostrarProdutos() {
		this.produto.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
	}
	
	private void cnpjIsNull(String cnpj) {
		if (cnpj == null) {
			throw new NullPointerException("O cnpj não pode ficar null");
		}
	}
	
	private void cnpjIsEmpty(String cnpj) {
		if (cnpj.isEmpty() || cnpj.trim().equals("")) {
			throw new EmptyStringException("O cnpj não pode ficar vazio");
		}
	}
	
	private void cnpjHasWord(String cnpj) {
		if (cnpj.matches("^[a-zA-ZÁÂÃÀÇÉÊÍÓÔÕÚÜáâãàçéêíóôõúü]*$")) {
			throw new IllegalArgumentException("O cnpj contém letras");
		} else if (!cnpj.matches("^[0-9]*$")) {
			throw new IllegalArgumentException("O cnpj contém letras e números");
		}
	}
	
	private void cnpjSizeValidation(String cnpj) {
		if (cnpj.length() != 14) {
			throw new IllegalArgumentException("O cnpj está errado");
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
