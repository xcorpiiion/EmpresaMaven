package br.com.contmatic.empresa;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.contmatic.constantes.ValidationNullEmptyStringRule;

public class Produto {

    private String nome;

    private BigDecimal preco;

    private Integer estoque;

    public Produto(String nome, BigDecimal preco, Integer estoque) {
        setNome(nome);
        setPreco(preco);
        setEstoque(estoque);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        ValidationNullEmptyStringRule.nomeIsEmpty(nome);
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
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

    private void precoIsNull(BigDecimal preco) {
        if (preco == null) {
            throw new NullPointerException("O preço ou null");
        }
    }

    private void estoqueIsNull(Integer estoque) {
        if (estoque == null) {
            throw new NullPointerException("O estoque está null");
        }
    }

    private void estoqueIsNegative(Integer estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("O estoque não pode ser negativo");
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(nome).append(preco).toHashCode();
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
        return new EqualsBuilder().append(nome, other.nome).append(preco, other.preco).isEquals();
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Nome: ").append(getNome()).append(", R$: ").append(getPreco()).append(", Quantidade em estoque: ").append(getEstoque()).toString();
    }
}
