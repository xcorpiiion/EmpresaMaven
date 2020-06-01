package br.com.contmatic.empresa;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;
import br.com.contmatic.constantes.Mensagem;

/**
 * The Class Produto.
 */
public class Produto {

	/** The nome. */
	@NotBlank(message = Mensagem.VALOR_ESTA_VAZIO)
	@NotNull(message = Mensagem.VALOR_ESTA_NULLO)
	@NotEmpty(message = Mensagem.VALOR_ESTA_VAZIO)
	@Length(min = 3, max = 50, message = Mensagem.NOME_E_MUITO_PEQUENO)
	private String nome;

	/** The preco. */
	@NotNull(message = Mensagem.VALOR_ESTA_NULLO)
	@Min(value = 1, message = Mensagem.PRECISA_SER_UM_VALOR_MAIOR)
	private BigDecimal preco;

	/** The estoque. */
	@NotNull(message = Mensagem.VALOR_ESTA_NULLO)
	@Min(value = 1, message = Mensagem.VALOR_NAO_E_VALIDO)
	private Integer estoque;

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the preco.
	 *
	 * @return the preco
	 */
	public BigDecimal getPreco() {
		return preco;
	}

	/**
	 * Sets the preco.
	 *
	 * @param preco the new preco
	 */
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	/**
	 * Gets the estoque.
	 *
	 * @return the estoque
	 */
	public Integer getEstoque() {
		return estoque;
	}

	/**
	 * Sets the estoque.
	 *
	 * @param estoque the new estoque
	 */
	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(nome).append(preco).toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return new EqualsBuilder().append(nome, other.nome).append(preco, other.preco).isEquals();
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return reflectionToString(this, JSON_STYLE);
	}
}
