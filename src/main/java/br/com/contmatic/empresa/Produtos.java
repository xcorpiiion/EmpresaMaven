package br.com.contmatic.empresa;

public class Produtos {
	
	private String nome;
	
	private Double preco;
	
	private Integer estoque;
	
	public Produtos(String nome, Double preco, Integer estoque) throws Exception {
		setNome(nome);
		setPreco(preco);
		setEstoque(estoque);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {
		nomeIsEmpty(nome);
		nomeIsNull(nome);
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) throws Exception {
		precoIsNull(preco);
		this.preco = preco;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) throws Exception {
		estoqueIsNull(estoque);
		estoqueIsNegative(estoque);
		this.estoque = estoque;
	}
	
	private void nomeIsNull(String nome) throws Exception {
		if(nome == null) {
			throw new Exception("O nome do produto está null");
		}
	}
	
	private void nomeIsEmpty(String nome) throws Exception {
		if(nome.isEmpty() || nome.trim().equals("")) {
			throw new Exception("O nome do produto está vazio");
		}
	}
	
	private void precoIsNull(Double preco) throws Exception {
		if(preco == null) {
			throw new Exception("O preço ou null");
		}
	}
	
	private void estoqueIsNull(Integer estoque) throws Exception {
		if(estoque == null) {
			throw new Exception("O estoque está null");
		}
	}
	
	private void estoqueIsNegative(Integer estoque) throws Exception {
		if(estoque < 0) {
			throw new Exception("O estoque não pode ser negativo");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Produtos other = (Produtos) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Nome: " + nome + ", R$: " + preco + ", Quantidade em estoque: " + estoque;
	}
	
	
}
