package br.com.empresa;

public class Produtos {
	private String nome;
	private Double preco;
	private Integer estoque;
	
	public Produtos(String nome, Double preco, Integer estoque) throws Exception {
		this.nome = nome;
		this.preco = preco;
		this.estoque = estoque;
		verificarDados(nome, preco, estoque);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
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

	public void verificarDados(String nome, Double preco, Integer estoque) throws Exception {
		if(nome == null || nome.isEmpty()) {
			throw new Exception("O nome do produto está vazio ou null");
		}
		
		if(preco == null) {
			throw new Exception("O nome do preõ está vazio ou null");
		}
		
		if(estoque == null) {
			throw new Exception("O nome do estoque está null");
		}
	}
	
	@Override
	public String toString() {
		return "Nome: " + nome + ", R$: " + preco + ", Quantidade em estoque: " + estoque;
	}
	
	
}
