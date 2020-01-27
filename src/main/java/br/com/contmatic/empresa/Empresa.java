package br.com.contmatic.empresa;

import java.util.List;
import br.com.contmatic.constantes.ValidationNullOrEmpty;
import br.com.contmatic.services.EmptyStringException;
import br.com.contmatic.services.StringSizeException;

public class Empresa {

	private String nome;

	private String email;

	private String cnpj;

	private Endereco endereco;

	private List<Produto> produtos;

	private List<Funcionario> funcionarios;

	private List<Cliente> clientes;

	public Empresa(String nome, String email, List<Produto> produtos, String cnpj, Endereco endereco) {
		setNome(nome);
		setEmail(email);
		setProduto(produtos);
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

	public void setProduto(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Produto> getProduto() {
		return produtos;
	}

	public void setFuncionario(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Funcionario> getFuncionario() {
		return funcionarios;
	}

	public void setCliente(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Cliente> getCliente() {
		return clientes;
	}

	public boolean produtoExiste(String nome) {
		return produtos.stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nome));
	}

	public void mostrarProdutos() {
		produtos.sort((p1, p2) -> p1.getNome().compareTo(p2.getNome()));
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
			throw new StringSizeException("O cnpj está errado");
		}
	}
	
	public boolean clienteExiste(Empresa lojaCliente, String nome, String email) {
		return lojaCliente.getCliente().stream()
				.anyMatch(c -> c.getNome().equalsIgnoreCase(nome) && c.getEmail().equalsIgnoreCase(email));
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
		return "Empresa [nome=" + getNome() + ", email=" + getEmail() + ", cnpj=" + getCnpj() + ", endereco="
				+ getEndereco() + ", produto=" + getProduto() + ", funcionario=" + getFuncionario() + ", cliente="
				+ getCliente() + "]";
	}

}
