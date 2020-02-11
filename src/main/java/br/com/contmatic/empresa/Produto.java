package br.com.contmatic.empresa;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

import br.com.contmatic.constantes.Constante;

public class Produto {

    @NotBlank(message = Constante.NOME_NAO_PODE_ESTA_VAZIO)
    @NotEmpty(message = Constante.NOME_NAO_PODE_ESTA_VAZIO)
    @Length(min = 3, max = 50)
    private String nome;

    @NotNull(message = Constante.O_VALOR_ESTA_NULLO)
    @DecimalMin(value = "1.00", message = Constante.O_VALOR_NAO_E_VALIDO)
    private BigDecimal preco;

    @Min(value = 1)
    private Integer estoque;

    public Produto(String nome, BigDecimal preco, Integer estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }
    
    public Produto() { 
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
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
