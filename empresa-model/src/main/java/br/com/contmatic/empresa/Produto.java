package br.com.contmatic.empresa;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static br.com.contmatic.utils.Mensagem.*;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

/**
 * The Class Produto.
 */
public class Produto {

    /**
     * The nome.
     */
    @NotBlank(message = NOME_PRODUTO_VAZIO)
    @NotNull(message = NOME_PRODUTO_VAZIO)
    @NotEmpty(message = NOME_PRODUTO_VAZIO)
    @Length(min = 3, max = 50, message = NOME_PRODUTO_TAMANHO)
    private String nome;

    /**
     * The preco.
     */
    @NotNull(message = PRECO_PRODUTO_VAZIO)
    @Min(value = 1, message = PRECO_PRODUTO_TAMANHO)
    private BigDecimal preco;

    /**
     * The estoque.
     */
    @Min(value = 1, message = ESTOQUE_PRODUTO_VAZIO)
    private Integer estoque;

    public Produto() {
    }

    public Produto(String nome, BigDecimal preco, Integer estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

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
