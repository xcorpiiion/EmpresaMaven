package br.com.contmatic.empresa;

import br.com.contmatic.constantes.ValidationNullOrEmpty;

public class Produto {
	
	private String nome;
	
	private Double preco;
	
	private Integer estoque;
	
	public Produto(String nome, Double preco, Integer estoque){
		setNome(nome);
		setPreco(preco);
		setEstoque(estoque);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		ValidationNullOrEmpty.nomeIsNull(nome);
		ValidationNullOrEmpty.nomeIsEmpty(nome);
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		precoIsNull(preco);
		this.preco = preco;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		estoqueIsNull(estoque);
		estoqueIsNegative(estoque);
		this.estoque = estoque;
	}
	
	private void precoIsNull(Double preco) {
		if(preco == null) {
			throw new NullPointerException("O preço ou null");
		}
	}
	
	private void estoqueIsNull(Integer estoque) {
		if(estoque == null) {
			throw new NullPointerException("O estoque está null");
		}
	}
	
	private void estoqueIsNegative(Integer estoque) {
		if(estoque < 0) {
			throw new IllegalArgumentException("O estoque não pode ser negativo");
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
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Nome: " + getNome() + ", R$: " + getPreco() + ", Quantidade em estoque: " + getEstoque();
	}
	
	
}
