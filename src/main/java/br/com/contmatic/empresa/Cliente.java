package br.com.contmatic.empresa;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import br.com.contmatic.constantes.ValidationNullOrEmpty;

public class Cliente {

	private String nome;

	private String email;

	private Date dataNascimento;

	private BigDecimal dinheiroCarteira;

	private Endereco endereco;

	private List<Produto> carrinhoProduto;

	private List<Produto> produtosComprados;

	public Cliente(String nome, String email, Date dataNascimento, Endereco endereco) {
		setNome(nome);
		setEmail(email);
		ValidationNullOrEmpty.dataNascimentoIsNull(dataNascimento);
		this.dataNascimento = dataNascimento;
		setEndereco(endereco);
	}
	
	
	public BigDecimal getDinheiroCarteira() {
		return dinheiroCarteira;
	}

	public void setDinheiroCarteira(BigDecimal dinheiroCarteira) {
		this.dinheiroCarteira = dinheiroCarteira;
	}

	public void setCarrinhoProduto(List<Produto> carrinhoProduto) {
		this.carrinhoProduto = carrinhoProduto;
	}

	public List<Produto> getCarrinhoProduto() {
		return carrinhoProduto;
	}
	

	public void setProdutosComprados(List<Produto> produtosComprados) {
		this.produtosComprados = produtosComprados;
	}

	public List<Produto> getProdutosComprados() {
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

	public void setEmail(String email) {
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
		ValidationNullOrEmpty.lojaIsNull(loja);
		loja.mostrarProdutos();
		if (produtoExisteNaLoja(nomeProduto, loja)) {

			if (qtdProdutoAddCarrinho < 1) {
				throw new IllegalArgumentException("Você precisa pelo menos add 1 produto ao carrinho");
			}
			if (loja.getProduto().stream().anyMatch(prod -> prod.getNome().equalsIgnoreCase(nomeProduto)
					&& prod.getEstoque() < qtdProdutoAddCarrinho)) {
				throw new IllegalArgumentException(
						"A quantidade de produtos que você quer colocar no carrinho é maior do que a"
								+ " quantidade em estoque");
			}
			for (int i = 0; i < qtdProdutoAddCarrinho; i++) {
				cliente.carrinhoProduto.addAll(loja.getProduto().stream()
						.filter(prod -> prod.getNome().equalsIgnoreCase(nomeProduto)).collect(Collectors.toList()));
			}
		} else {
			throw new IllegalArgumentException("O produto não existe");
		}

	}

	public void compraProduto(Cliente cliente, String nomeProduto, int qtdProdutosCompra) {
		if (!produtoEstaNoCarrinho(nomeProduto, cliente)) {
			throw new IllegalArgumentException("O produto não existe no carrinho");
		}
		BigDecimal totalPreco = new BigDecimal(0);
		for (Produto prod : cliente.getCarrinhoProduto()) {
			if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
				if (qtdProdutosCompra > prod.getEstoque()) {
					throw new IllegalArgumentException(
							"A quantidade de produtos que você quer comprar é maior do que a "
									+ "quantidade em estoque");
				}
				totalPreco.add(prod.getPreco());
			}
		}
		if (cliente.getDinheiroCarteira().compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Dinheiro insulficiente para comprar produtos");
		}
		cliente.dinheiroCarteira.subtract(totalPreco);

		for (Produto prod : cliente.getCarrinhoProduto()) {
			if (prod.getNome().equalsIgnoreCase(nomeProduto)) {
				cliente.produtosComprados.add(prod);
			}
		}
	}

	public void cadastrarCliente(String nome, String email, Date dataNascimento, Empresa loja,
			Endereco endereco) {
		ValidationNullOrEmpty.lojaIsNull(loja);
		if (clienteExiste(loja, nome, email)) {
			throw new IllegalArgumentException("O cliente já está cadastrado");
		}
		loja.getCliente().add(new Cliente(nome, email, dataNascimento, endereco));
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
		return "Cliente [nome=" + getNome() + ", email=" + getEmail() + ", dataNascimento=" + getDataNascimento()
				+ ", dinheiroCarteira=" + getDinheiroCarteira() + ", endereco=" + getEndereco() + ", carrinhoProduto="
				+ getCarrinhoProduto() + ", produtosComprados=" + getProdutosComprados() + "]";
	}

}
