package br.com.contmatic.empresa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cliente extends Pessoa {

	private Double dinheiroCarteira;

	private Endereco endereco;

	private List<Produtos> carrinhoProduto = new ArrayList<>();

	private List<Produtos> produtosComprados = new ArrayList<>();

	public Cliente(String nome, String email, Double dinheiro, SimpleDateFormat dataNascimento, Endereco endereco)
			throws Exception {
		super(nome, email, dataNascimento);
		this.dinheiroCarteira = dinheiro;
		this.endereco = endereco;
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

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void addItensCarrinho(Cliente cliente, Empresa loja, String nomeProduto, int qtdProdutoAddCarrinho)
			throws Exception {
		if (loja.getProduto() == null) {
			throw new Exception("O produto esta null");
		}
		loja.mostrarProdutos();
		if (loja.getProduto().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto))) {

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
		if (cliente.getCarrinhoProduto() == null) {
			throw new Exception("O carrinho esta null");
		}
		if (!cliente.getCarrinhoProduto().stream().allMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto))) {
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
		if (loja == null) {
			throw new Exception("A loja está nulla");
		}
		if (loja.getCliente() == null) {
			throw new Exception("O cliente está nullo");
		}
		if (loja.getCliente().stream()
				.anyMatch(c -> c.getNome().equalsIgnoreCase(nome) && c.getEmail().equalsIgnoreCase(email))) {
			throw new Exception("O cliente já está cadastrado");
		}
		loja.getCliente().add(new Cliente(nome, email, dinheiro, dataNascimento, endereco));
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
