package br.com.contmatic.empresa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cliente {

	private String nome;

	private String email;

	private SimpleDateFormat dataNascimento = new SimpleDateFormat("dd/MM/yyyy");

	private Double dinheiroCarteira;

	private Endereco endereco;

	private List<Produtos> carrinhoProduto = new ArrayList<>();

	private List<Produtos> produtosComprados = new ArrayList<>();

	public Cliente(String nome, String email, Double dinheiroCarteira, SimpleDateFormat dataNascimento,
			Endereco endereco) throws Exception {
		this.nome = nome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.dinheiroCarteira = dinheiroCarteira;
		this.endereco = endereco;
		dataNascimentoIsNull(dataNascimento);
		emailIsNull(email);
		nomeIsNull(nome);
		dinheiroIsNegative(dinheiroCarteira);
	}

	public Double getDinheiroCarteira() {
		return dinheiroCarteira;
	}

	public List<Produtos> getCarrinhoProduto() {
		return carrinhoProduto;
	}

	public List<Produtos> getProdutosComprados() {
		return produtosComprados;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) throws Exception {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {
		nomeIsNull(nome);
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		emailIsNull(email);
		this.email = email;
	}

	public SimpleDateFormat getDataNascimento() {
		return dataNascimento;
	}

	private void nao_deve_aceitar_produto_null(Empresa loja) throws Exception {
		if (loja.getProduto() == null) {
			System.out.println("Entrou aqui");
			throw new Exception("O produto esta null");
		}
	}

	// verifica se o produto existe e retorna true ou false
	public boolean produtoExisteNaLoja(String nomeProduto, Empresa loja) {
		return loja.getProduto().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto));
	}

	// verifica se o produto existe e retorna true ou false
	public boolean produtoEstaNoCarrinho(String nomeProduto, Cliente carrinhoCliente) {
		return carrinhoCliente.getCarrinhoProduto().stream()
				.anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto));
	}

	public boolean clienteExiste(Empresa lojaCliente, String nome, String email) {
		return lojaCliente.getCliente().stream()
				.anyMatch(c -> c.getNome().equalsIgnoreCase(nome) && c.getEmail().equalsIgnoreCase(email));
	}
	
	public void addItensCarrinho(Cliente cliente, Empresa loja, String nomeProduto, int qtdProdutoAddCarrinho)
			throws Exception {
		nao_deve_aceitar_produto_null(loja);
		loja.mostrarProdutos();
		if (produtoExisteNaLoja(nomeProduto, loja)) {

			if (qtdProdutoAddCarrinho < 1) {
				throw new Exception("Você precisa pelo menos add 1 produto ao carrinho");
			}
			if (loja.getProduto().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto)
					&& prod.getEstoque() < qtdProdutoAddCarrinho)) {
				throw new Exception("A quantidade de produtos que você quer colocar no carrinho é maior do que a"
						+ " quantidade em estoque");
			}
			for (int i = 0; i < qtdProdutoAddCarrinho; i++) {
				cliente.carrinhoProduto.addAll(loja.getProduto().stream()
						.filter(prod -> prod.getNome().equalsIgnoreCase(nomeProduto)).collect(Collectors.toList()));
			}
		} else {
			throw new Exception("O produto não existe");
		}

	}

	public void addDinheiroCarteira(Double dinheiro) throws Exception {
		if (dinheiro <= 0) {
			throw new Exception("Você precisa colocar um valor acima de zero para adicionar a carteira");
		}
		this.dinheiroCarteira += dinheiro;
	}

	public void compraProduto(Cliente cliente, String nomeProduto, int qtdProdutosCompra) throws Exception {
		carrinhoIsNull();
		if (!produtoEstaNoCarrinho(nomeProduto, cliente)) {
			throw new Exception("O produto não existe no carrinho");
		}
		double totalPreco = 0.0;
		for (Produtos prod : cliente.getCarrinhoProduto()) {
			if (prod.getNome().hashCode() == nomeProduto.hashCode()) {
				if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
					if (qtdProdutosCompra > prod.getEstoque()) {
						throw new Exception("A quantidade de produtos que você quer comprar é maior do que a "
								+ "quantidade em estoque");
					}
					totalPreco += prod.getPreco();
				}
			}
		}
		if (cliente.getDinheiroCarteira() < totalPreco) {
			throw new Exception("Dinheiro insulficiente para comprar produtos");
		}
		cliente.dinheiroCarteira -= totalPreco;

		for (Produtos prod : cliente.getCarrinhoProduto()) {
			if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
				cliente.produtosComprados.add(prod);
			}
		}
	}

	public void cadastrarCliente(String nome, String email, Double dinheiro, SimpleDateFormat dataNascimento,
			Empresa loja, Endereco endereco) throws Exception {
		lojaIsNull(loja);
		if (clienteExiste(loja, nome, email)) {
			throw new Exception("O cliente já está cadastrado");
		}
		loja.getCliente().add(new Cliente(nome, email, dinheiro, dataNascimento, endereco));
	}

	private void carrinhoIsNull() throws Exception {
		if (nome == null) {
			throw new Exception("O carrinho está null ou vazio");
		}
	}

	private void nomeIsNull(String nome) throws Exception {
		if (nome == null || nome.isEmpty() || nome.trim().equals("")) {
			throw new Exception("O nome está null ou vazio");
		}
	}

	private void emailIsNull(String email) throws Exception {
		if (email == null || email.isEmpty() || email.trim().equals("")) {
			throw new Exception("O email está null ou vazio");
		}
	}

	private void dataNascimentoIsNull(SimpleDateFormat dataNascimento) throws Exception {
		if (dataNascimento == null) {
			throw new NullPointerException("A data de nascimento está null");
		}
	}

	private void dinheiroIsNegative(Double dinheiro) throws Exception {
		if (dinheiro < 0) {
			throw new Exception("O valo não pode ser negativo");
		}
	}
	
	private void lojaIsNull(Empresa loja) {
		if (loja == null) {
			throw new NullPointerException("A loja está null");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public String toString(Cliente cliente) {
		return "------Dados do cliente------" + "\nNome: " + cliente.getNome() + "\nEmail: " + cliente.getEmail()
				+ ", Valor na carteira: " + cliente.getDinheiroCarteira() + "\nProdutos no carrinho: "
				+ cliente.carrinhoProduto
						.stream().map(p1 -> p1.getNome()).collect(Collectors.toList()).toString().replaceFirst(",", "")
				+ "\nProdutos comprados: "
				+ cliente.produtosComprados.stream().map(p1 -> p1.getNome()).collect(Collectors.toList()).toString()
						.replaceFirst(",", "")
				+ "\n------Endereço------" + "\nRua: " + cliente.getEndereco().getRua() + "\nNúmero residência: "
				+ cliente.getEndereco().getNumeroResidencia() + "\nBairro: " + cliente.getEndereco().getBairro()
				+ "\nCep: " + cliente.getEndereco().getCep() + "\nCidade: " + cliente.getEndereco().getCidade()
				+ "\nEstado: " + cliente.getEndereco().getEstado();
	}

}
