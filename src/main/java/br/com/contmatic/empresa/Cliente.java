package br.com.contmatic.empresa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import br.com.contmatic.constantes.ValidationNullOrEmpty;

public class Cliente {

	private String nome;

	private String email;

	private Date dataNascimento;

	private Double dinheiroCarteira;

	private Endereco endereco;

	private List<Produtos> carrinhoProduto = new ArrayList<>();

	private List<Produtos> produtosComprados = new ArrayList<>();

	public Cliente(String nome, String email, Double dinheiroCarteira, Date dataNascimento,
			Endereco endereco) {
		setNome(nome);
		setEmail(email);
		this.dataNascimento = dataNascimento;
		this.dinheiroCarteira = dinheiroCarteira;
		setEndereco(endereco);
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

	public void setEndereco(Endereco endereco) {
		ValidationNullOrEmpty.enderecoIsNull(endereco);
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		ValidationNullOrEmpty.nomeIsNull(nome);
		ValidationNullOrEmpty.nomeIsEmpty(nome);
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email){
		ValidationNullOrEmpty.emailIsNull(email);
		ValidationNullOrEmpty.emailIsEmpty(email);
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		ValidationNullOrEmpty.dataNascimentoIsNull(dataNascimento);
		this.dataNascimento = dataNascimento;
	}

	private void empresaIsNull(Empresa loja) {
		if (loja.getProduto() == null) {
			throw new RuntimeException("A empresa esta null");
		}
	}

	public boolean produtoExisteNaLoja(String nomeProduto, Empresa loja) {
		return loja.getProduto().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto));
	}

	public boolean produtoEstaNoCarrinho(String nomeProduto, Cliente carrinhoCliente) {
		return carrinhoCliente.getCarrinhoProduto().stream()
				.anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto));
	}

	public boolean clienteExiste(Empresa lojaCliente, String nome, String email) {
		return lojaCliente.getCliente().stream()
				.anyMatch(c -> c.getNome().equalsIgnoreCase(nome) && c.getEmail().equalsIgnoreCase(email));
	}
	
	public void addItensCarrinho(Cliente cliente, Empresa loja, String nomeProduto, int qtdProdutoAddCarrinho) {
		empresaIsNull(loja);
		loja.mostrarProdutos();
		if (produtoExisteNaLoja(nomeProduto, loja)) {

			if (qtdProdutoAddCarrinho < 1) {
				throw new RuntimeException("Você precisa pelo menos add 1 produto ao carrinho");
			}
			if (loja.getProduto().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto)
					&& prod.getEstoque() < qtdProdutoAddCarrinho)) {
				throw new RuntimeException("A quantidade de produtos que você quer colocar no carrinho é maior do que a"
						+ " quantidade em estoque");
			}
			for (int i = 0; i < qtdProdutoAddCarrinho; i++) {
				cliente.carrinhoProduto.addAll(loja.getProduto().stream()
						.filter(prod -> prod.getNome().equalsIgnoreCase(nomeProduto)).collect(Collectors.toList()));
			}
		} else {
			throw new RuntimeException("O produto não existe");
		}

	}

	public void addDinheiroCarteira(Double dinheiro) {
		if (dinheiro <= 0) {
			throw new RuntimeException("Você precisa colocar um valor acima de zero para adicionar a carteira");
		}
		this.dinheiroCarteira += dinheiro;
	}

	public void compraProduto(Cliente cliente, String nomeProduto, int qtdProdutosCompra)  {
		if (!produtoEstaNoCarrinho(nomeProduto, cliente)) {
			throw new RuntimeException("O produto não existe no carrinho");
		}
		double totalPreco = 0.0;
		for (Produtos prod : cliente.getCarrinhoProduto()) {
			if (prod.getNome().hashCode() == nomeProduto.hashCode()) {
				if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
					if (qtdProdutosCompra > prod.getEstoque()) {
						throw new RuntimeException("A quantidade de produtos que você quer comprar é maior do que a "
								+ "quantidade em estoque");
					}
					totalPreco += prod.getPreco();
				}
			}
		}
		if (cliente.getDinheiroCarteira() < totalPreco) {
			throw new RuntimeException("Dinheiro insulficiente para comprar produtos");
		}
		cliente.dinheiroCarteira -= totalPreco;

		for (Produtos prod : cliente.getCarrinhoProduto()) {
			if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
				cliente.produtosComprados.add(prod);
			}
		}
	}

	public void cadastrarCliente(String nome, String email, Double dinheiro, Date dataNascimento,
			Empresa loja, Endereco endereco) {
		ValidationNullOrEmpty.lojaIsNull(loja);
		if (clienteExiste(loja, nome, email)) {
			throw new RuntimeException("O cliente já está cadastrado");
		}
		loja.getCliente().add(new Cliente(nome, email, dinheiro, dataNascimento, endereco));
	}

	private void dinheiroIsNegative(Double dinheiro) {
		if (dinheiro < 0) {
			throw new RuntimeException("O valo não pode ser negativo");
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
	
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", email=" + email + ", dataNascimento=" + dataNascimento
				+ ", dinheiroCarteira=" + dinheiroCarteira + ", endereco=" + endereco + ", carrinhoProduto="
				+ carrinhoProduto + ", produtosComprados=" + produtosComprados + "]";
	}

}
