package br.com.contmatic.empresa;

import static br.com.contmatic.empresa.utils.FieldValidation.isNull;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringContaisWordAndNumber;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringEmpty;
import static br.com.contmatic.empresa.utils.FieldValidation.minAndMaxValue;

import java.math.BigDecimal;

public class Produto {
	
	private String nome;
	
	private BigDecimal preco;
	
	private Integer estoque;
	
	public Produto(String nome, BigDecimal preco, Integer estoque){
		this.setNome(nome);
		this.setPreco(preco);
		this.setEstoque(estoque);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		isNull(nome);
		isStringEmpty(nome);
		minAndMaxValue(2, 30, nome);
		isStringContaisWordAndNumber(nome);
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		isNull(preco);
		minAndMaxValue(BigDecimal.valueOf(1), BigDecimal.valueOf(300), preco);
		this.preco = preco;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		isNull(estoque);
		minAndMaxValue(1, 30, estoque);
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
