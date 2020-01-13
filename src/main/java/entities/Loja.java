package entities;

import java.util.ArrayList;
import java.util.List;

import enums.MotivoDemissao;
import enums.TipoContrato;

public class Loja {
	private String nome, email;
	private MotivoDemissao motivoDemissao;
	private TipoContrato tipoContrato;
	private List<Produtos> produto = new ArrayList<Produtos>();
	private List<Funcionario> funcionario = new ArrayList<Funcionario>();
	private List<Cliente> cliente = new ArrayList<Cliente>();
	
	public Loja(String nome, String email, List<Produtos> produto, List<Funcionario> funcionario,
			List<Cliente> cliente) {
		this.nome = nome;
		this.email = email;
		this.produto = produto;
		this.funcionario = funcionario;
		this.cliente = cliente;
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

	public void contratarFuncionario() {
		
	}
	
	public void demitirFuncionario() {
		
	}
	
	public void cadastrarCliente() {
		
	}
	
	public void cadastrarProduto() {
		
	}
	
	
	
}
