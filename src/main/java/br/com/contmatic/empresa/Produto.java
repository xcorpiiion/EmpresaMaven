package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Constante.PRODUTO;
import static br.com.contmatic.constantes.Constante.campoVazioOrNullMensagemPadrao;
import static br.com.contmatic.constantes.Constante.stringJustContainsWordMensagemPadrao;
import static br.com.contmatic.constantes.Constante.tamanhoCamposMensagemPadrao;
import static br.com.contmatic.empresa.utils.FieldValidation.isNull;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringContaisWordAndNumber;
import static br.com.contmatic.empresa.utils.FieldValidation.isStringEmpty;
import static br.com.contmatic.empresa.utils.FieldValidation.minAndMaxValue;
import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;

public class Produto {

	private String nome;

	private BigDecimal preco;

	private Integer estoque;

	public Produto(String nome, BigDecimal preco, Integer estoque) {
		this.setNome(nome);
		this.setPreco(preco);
		this.setEstoque(estoque);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		isStringEmpty(nome, campoVazioOrNullMensagemPadrao("nome", PRODUTO));
		minAndMaxValue(2, 30, nome, tamanhoCamposMensagemPadrao(3, 30, "nome", PRODUTO));
		isStringContaisWordAndNumber(nome, stringJustContainsWordMensagemPadrao("Nome", PRODUTO));
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		isNull(preco, campoVazioOrNullMensagemPadrao("preço", PRODUTO));
		minAndMaxValue(valueOf(1), valueOf(300), preco, tamanhoCamposMensagemPadrao(1, 300, "preço", PRODUTO));
		this.preco = preco;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		isNull(estoque, campoVazioOrNullMensagemPadrao("estoque", PRODUTO));
		minAndMaxValue(1, 30, estoque, tamanhoCamposMensagemPadrao(1, 30, "estoque", PRODUTO));
		this.estoque = estoque;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
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
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Produto [nome=").append(nome).append(", preco=").append(preco).append(", estoque=")
				.append(estoque).append("]");
		return builder.toString();
	}

}
